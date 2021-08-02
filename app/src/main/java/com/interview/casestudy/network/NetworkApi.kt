package com.interview.casestudy.network

import com.interview.casestudy.model.BlobResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("api/jsonBlob/a07152f5-775c-11eb-a533-c90b9d55001f")
    suspend fun getJsonBlob(): BlobResponse
}