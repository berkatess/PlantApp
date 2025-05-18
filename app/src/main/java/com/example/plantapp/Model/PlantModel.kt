package com.example.plantapp.Model

data class PlantModel(
    val name: String,
    val imageUrl: String,
    val type: PlantType
)

enum class PlantType {
    QUESTION,
    CATEGORY
}
