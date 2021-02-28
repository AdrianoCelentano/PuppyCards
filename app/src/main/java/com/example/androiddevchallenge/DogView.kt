package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogView(
    modifier: Modifier = Modifier,
    dog: Dog,
) {
    Box(modifier = modifier.fillMaxSize()) {
        CoilImage(
            modifier = Modifier.fillMaxHeight(),
            data = dog.image,
            contentDescription = "Dog",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(gradient())
            )
            DogDescription(dog)
        }
    }
}

@Composable
private fun DogDescription(dog: Dog) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White.copy(alpha = 0.5f))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = dog.name,
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Outlined.Favorite,
                contentDescription = "Info"
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = dog.age,
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
private fun gradient() = remember {
    Brush.verticalGradient(
        0f to Color.White.copy(alpha = 0f),
        1000f to Color.White.copy(alpha = 0.5f)
    )
}