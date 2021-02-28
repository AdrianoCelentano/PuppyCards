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

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DogsCards(
    dogs: MutableState<List<Dog>>,
    selectedDog: MutableState<Dog?>
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        dogs.value.forEachIndexed { index, dog ->
            SwipeCard(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .align(BiasAlignment(0f, -0.8f)),
                onSwiped = { removeDog(dogs, index) }
            ) {
                DogView(dog = dog)
            }
        }

        Image(
            modifier = Modifier
                .fillMaxSize(0.2f)
                .clickable { selectedDog.value = dogs.value.last() }
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            imageVector = Icons.Outlined.ThumbUp,
            contentDescription = "Like"
        )
    }
}

private fun removeDog(
    dogs: MutableState<List<Dog>>,
    index: Int
) {
    dogs.value = dogs.value.toMutableList()
        .apply { removeAt(index) }
}
