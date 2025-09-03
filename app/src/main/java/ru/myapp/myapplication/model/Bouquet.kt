package ru.myapp.myapplication.model

import ru.myapp.myapplication.database.Dbo

data class Bouquet(
    val flowerCount: Int,
    val bouquet: List<Dbo>,
    val decoration: String? = null
)