package com.example.up_kasatkina.model

import kotlinx.serialization.Serializable

@Serializable
data class products (
    val id: String,
    val title: String,
    val category_id: String,
    val cost: Float,
    val description: String,
    val is_best_seller: Boolean,
    val image: String
)

