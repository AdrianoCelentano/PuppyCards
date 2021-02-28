/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.tween
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun SwipeCard(
    modifier: Modifier,
    onSwiped: () -> Unit,
    content: @Composable () -> Unit
) {

    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    val elevation = if (offset.value == Offset.Zero) 0.dp else 4.dp
    val rotation = calculateRotation(offset)

    Card(
        elevation = elevation,
        modifier = modifier
            .rotate(rotation)
            .pointerInput(Unit) {

                val decay = splineBasedDecay<Offset>(this)

                coroutineScope {

                    while (isActive) {

                        val velocityTracker = handleGesture(offset, this)
                        val velocity = calculateVelocity(velocityTracker)
                        val flingOffset = decay.calculateTargetValue(
                            Offset.VectorConverter,
                            offset.value,
                            velocity
                        )
                        val shouldSlideBack = shouldSlideBack(flingOffset)
                        val targetOffset = when {
                            shouldSlideBack -> Offset.Zero
                            isVerticalDrag(flingOffset) -> calculateVerticalSlideOut(flingOffset)
                            else -> calculateHorizontalSlideOut(flingOffset)
                        }
                        launch {
                            offset.animateTo(
                                animationSpec = tween(600),
                                targetValue = targetOffset,
                                initialVelocity = velocity
                            )
                            if (shouldSlideBack.not()) onSwiped()
                        }
                    }
                }
            }
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(placeable.width, placeable.height) {
                    placeable.placeRelative(
                        offset.value.x.toInt(),
                        offset.value.y.toInt()
                    )
                }
            },
    ) {
        content()
    }
}

@Composable
private fun calculateRotation(offset: Animatable<Offset, AnimationVector2D>): Float {
    return when (val horizontalOffset = offset.value.x.dp) {
        0.dp -> 0f
        else -> horizontalOffset / 4.dp * 0.10f
    }
}

private suspend fun PointerInputScope.handleGesture(
    anim: Animatable<Offset, AnimationVector2D>,
    coroutineScope: CoroutineScope
): VelocityTracker {

    val velocityTracker = VelocityTracker()
    val pointerId = awaitPointerEventScope { awaitFirstDown().id }
    anim.stop()

    awaitPointerEventScope {
        drag(pointerId) { change ->
            coroutineScope.launch {
                anim.snapTo(anim.value + change.positionChange())
            }

            velocityTracker.addPosition(
                change.uptimeMillis,
                change.position
            )
        }
    }
    return velocityTracker
}

private fun PointerInputScope.calculateHorizontalSlideOut(offset: Offset): Offset = with(offset) {
    val slope = y / x
    val horizontalTarget = x + if (x > 0) 2 * size.width else -2 * size.width
    return Offset(
        x = horizontalTarget,
        y = horizontalTarget * slope,
    )
}

private fun PointerInputScope.calculateVerticalSlideOut(offset: Offset): Offset = with(offset) {
    val slope = y / x
    val verticalTarget = y + if (y > 0) 2 * size.height else -2 * size.height
    return Offset(
        x = verticalTarget / slope,
        y = verticalTarget
    )
}

private fun isVerticalDrag(animationTarget: Offset) =
    animationTarget.y.absoluteValue > animationTarget.x.absoluteValue

private fun PointerInputScope.shouldSlideBack(animationTarget: Offset): Boolean {
    return animationTarget.x.absoluteValue < size.width / 8 && animationTarget.y.absoluteValue < size.height / 8
}

private fun calculateVelocity(remainingVelocity: VelocityTracker): Offset {
    val (x, y) = remainingVelocity.calculateVelocity()
    return Offset(x, y)
}
