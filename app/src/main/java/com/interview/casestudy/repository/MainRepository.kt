package com.interview.casestudy.repository

import com.interview.casestudy.model.BlobResponse
import com.interview.casestudy.network.NetworkApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val networkApi: NetworkApi
) {
    var username: String = ""
    suspend fun getBlobResponse(): BlobResponse = networkApi.getJsonBlob()

}