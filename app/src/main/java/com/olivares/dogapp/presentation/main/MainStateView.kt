package com.olivares.dogapp.presentation.main

data class MainStateView(
    val dogs: List<String>? = null,
    val error: Int? = null
)