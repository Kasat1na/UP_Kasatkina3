package com.example.up_kasatkina.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
@Serializable
data class profiles (
    val id: String,
    val created_at: Instant,
    val user_id: String,
    val photo: String,
    val firstname: String,
    val lastname: String,
    val address: String,
    val phone: String
)

