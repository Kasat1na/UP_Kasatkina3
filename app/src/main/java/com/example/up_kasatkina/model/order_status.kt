package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class order_status (
    val id: String,
    val created_at: Instant,
    val name: String
)

