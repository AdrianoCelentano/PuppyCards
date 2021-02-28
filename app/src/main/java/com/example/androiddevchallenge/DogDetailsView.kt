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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun DogDetailsView(dog: MutableState<Dog?>, dogValue: Dog) {

    val cardAlignment = remember { Animatable(-0.8f) }
    val cardSize = remember { Animatable(0.8f) }
    LaunchedEffect(Unit) {
        cardAlignment.animateTo(-1f)
        cardSize.animateTo(1f)
    }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(cardSize.value)
                .clickable {
                    coroutineScope.launch {
                        cardAlignment.animateTo(-0.8f)
                        cardSize.animateTo(0.8f)
                        dog.value = null
                    }
                }
                .align(BiasAlignment(0f, cardAlignment.value)),
        ) {
            DogView(dog = dogValue)
        }
    }
}
