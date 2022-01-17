package com.olivares.dogapp.domain

import com.olivares.dogapp.data.DogsRepository
import javax.inject.Inject

class GetImagesByBreedUseCase @Inject constructor(
    private val repository: DogsRepository
) : UseCase<List<String>, String> {
    override suspend fun run(breed: String): Result<List<String>> {
        return repository.getImagesByBreed(breed)
    }
}