package com.example.androiddevchallenge

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.LaunchedEffect
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
