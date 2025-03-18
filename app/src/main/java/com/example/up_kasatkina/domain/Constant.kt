package com.example.up_kasatkina.domain

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.postgrest.Postgrest

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://uidpbsiaihibukvzgiqb.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVpZHBic2lhaWhpYnVrdnpnaXFiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDIwNTIwODAsImV4cCI6MjA1NzYyODA4MH0.Bv-HDkjOX7NpNbEtRxDYw37X3wbkvc6LI8vBfXmaqBI"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}