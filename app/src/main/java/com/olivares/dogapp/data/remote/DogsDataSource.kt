package com.olivares.dogapp.data.remote

interface DogsDataSource {
    suspend fun getDogs(): Result<List<String>>
    suspend fun getImagesByBreed(breed: String): Result<List<String>>
}