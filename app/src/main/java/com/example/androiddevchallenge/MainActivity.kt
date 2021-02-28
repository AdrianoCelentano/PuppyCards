package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = getColor(android.R.color.black)

        setContent {
            MyTheme {

                val dogs = remember { mutableStateOf(dogList) }
                val selectedDog = remember { mutableStateOf<Dog?>(null) }

                val immutableDog = selectedDog.value
                if (immutableDog == null) DogsCards(dogs, selectedDog)
                else DogDetailsView(selectedDog, immutableDog)
            }
        }
    }
}