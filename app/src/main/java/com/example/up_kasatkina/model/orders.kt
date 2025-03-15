package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class orders (
    val id: Int,
    val created_at: Instant,
    val email: String,
    val phone: String,
    val address: String,
    val user_id: String,
    val payment_id: String,
    val delivery_coast: Int,
    val status_id: String
)

