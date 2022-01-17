package com.olivares.dogapp.data

import com.olivares.dogapp.data.remote.DogsDataSource
import javax.inject.Inject

class DogsRepositoryData @Inject constructor(
    private val dataSource: DogsDataSource
) : DogsRepository {

    override suspend fun getDogs(): Result<List<String>> {
        return dataSource.getDogs()
    }

    override suspend fun getImagesByBreed(breed: String): Result<List<String>> {
        return dataSource.getImagesByBreed(breed)
    }
}