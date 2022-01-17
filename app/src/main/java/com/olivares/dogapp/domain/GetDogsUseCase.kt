package com.olivares.dogapp.domain

import com.olivares.dogapp.data.DogsRepository
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(
    private val repository: DogsRepository
) : UseCase<List<String>, Unit?> {

    override suspend fun run(params: Unit?): Result<List<String>> {
        return repository.getDogs()
    }
}