package com.example.up_kasatkina.model

import kotlinx.serialization.Serializable
import java.sql.Timestamp
import kotlinx.datetime.Instant

@Serializable
data class actions(
    val id: String,
    val created_at: Instant,
    val photo: String
)