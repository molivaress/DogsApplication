package com.olivares.dogapp.data

interface DogsRepository {

    suspend fun getDogs(): Result<List<String>>
    suspend fun getImagesByBreed(breed: String): Result<List<String>>
}