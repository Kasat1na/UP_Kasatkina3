package com.example.up_kasatkina.domain

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.postgrest.Postgrest

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://kvmxvtqtknnprpcbzugi.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imt2bXh2dHF0a25ucHJwY2J6dWdpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjkxNjE0MDQsImV4cCI6MjA0NDczNzQwNH0.gFPRh2Ce5rp228eSuNtxHYmFFpxubWa8GxeC87EUz08"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}