package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class orders_items (
    val id: String,
    val created_at: Instant,
    val title: String,
    val coast: Float,
    val count: Int,
    val order_id: Int,
    val product_id: String
)
