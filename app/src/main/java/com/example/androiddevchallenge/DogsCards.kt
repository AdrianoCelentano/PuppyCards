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
