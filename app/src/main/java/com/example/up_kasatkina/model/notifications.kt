package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class notifications (
    val id: String,
    val created_at: Instant,
    val text: String,
    val title: String,
    val user_id: String,
    val is_read: Boolean,
)

