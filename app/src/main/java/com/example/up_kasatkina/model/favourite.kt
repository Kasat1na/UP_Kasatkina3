package com.example.up_kasatkina.model

import kotlinx.serialization.Serializable

@Serializable
data class favourite (
    val id: String = "",
    val product_id: String,
    val user_id: String,
)

