package com.olivares.dogapp.data.remote

import com.olivares.dogapp.commons.NetworkHandler
import com.olivares.dogapp.data.DogApiRest
import com.olivares.dogapp.data.remote.model.toListDomainDogs
import com.olivares.dogapp.data.remote.model.toListDomainImages
import javax.inject.Inject

class DogsRemoteDataSource @Inject constructor(
    private val dogApiRestRest: DogApiRest,
    override val networkHandler: NetworkHandler
) : DogsDataSource, ApiService() {

    override suspend fun getDogs(): Result<List<String>> {
        return request { dogApiRestRest.getAllDogs() }
            .map { it.toListDomainDogs() }
    }

    override suspend fun getImagesByBreed(breed: String): Result<List<String>> {
        return request { dogApiRestRest.getImages(breed) }
            .map { model -> model.toListDomainImages() }
    }
}