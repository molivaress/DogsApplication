package com.olivares.dogapp.domain

interface UseCase<out Type, in Params> {

    suspend fun run(params: Params): Result<Type>
}