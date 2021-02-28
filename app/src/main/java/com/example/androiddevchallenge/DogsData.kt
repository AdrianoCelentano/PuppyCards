package com.example.androiddevchallenge

data class Dog(
    val image: String,
    val name: String,
    val age: String,
)

val dogList = listOf(
    Dog(
        image = "https://s3.india.com/wp-content/uploads/2020/08/5170590074_714d36db83_b.jpg",
        name = "Bello",
        age = "2 months",
    ),
    Dog(
        image = "https://dogtime.com/assets/uploads/2011/03/puppy-development-1280x720.jpg",
        name = "Casper",
        age = "1 month",
    ),
    Dog(
        image = "https://www.petmd.com/sites/default/files/styles/article_image/public/petmd-puppy-weight.jpg?itok=IwMOwGSX",
        name = "Ben",
        age = "2 months",
    ),
    Dog(
        image = "https://images.unsplash.com/photo-1591160690555-5debfba289f0?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8cHVwcHl8ZW58MHx8MHw%3D&ixlib=rb-1.2.1&w=1000&q=80",
        name = "Sunny",
        age = "1 month",
    ),
    Dog(
        image = "https://i.pinimg.com/originals/e6/3d/2c/e63d2c80d0603f41f9455a5389694fae.jpg",
        name = "Jack",
        age = "2 months",
    ),
    Dog(
        image = "https://9ed48207422fa7fc5013-a6297eb5ec0f30e883355c8680f3b2d6.ssl.cf2.rackcdn.com/35585131450_6-20190322154216-20190322154226-1000x1000.jpg",
        name = "Bella",
        age = "1 month",
    ),
)
