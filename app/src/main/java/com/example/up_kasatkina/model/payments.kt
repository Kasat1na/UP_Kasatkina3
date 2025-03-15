package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class payments (
    val id: String,
    val created_at: Instant,
    val user_id: String,
    val card_name: String,
    val card_number: Int
)

