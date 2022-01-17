package com.olivares.dogapp.data.remote

import com.olivares.dogapp.commons.NetworkHandler
import com.olivares.dogapp.commons.exceptions.GeneralError
import com.olivares.dogapp.commons.exceptions.NetworkConnection
import com.olivares.dogapp.commons.exceptions.ServerError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber

abstract class ApiService {
    abstract val networkHandler: NetworkHandler

    suspend fun <T> request(
        default: T? = null,
        call: suspend () -> Response<T>
    ): Result<T> = withContext(Dispatchers.IO) {
        return@withContext when (networkHandler.isConnected) {
            true -> performRequest(call, default)
            false, null -> Result.failure(NetworkConnection())
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private suspend fun <T> performRequest(
        call: suspend () -> Response<T>,
        default: T? = null
    ): Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) }
                    ?: (default?.let { Result.success(it) } ?: Result.failure(GeneralError()))
            } else {
                return Result.failure(GeneralError())
            }
        } catch (exception: Throwable) {
            Timber.d("exception .. $exception")
            Result.failure(ServerError())
        }
    }
}