package com.example.shared.responsemodel

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.takeFrom

class DogServiceKtor {
    private val client = HttpClient {
        install(JsonFeature)
    }
    private val baseUrl = "https://dog.ceo/api/"

    suspend fun getBreeds(): NameResult = client.get("${baseUrl}breeds/list")
    suspend fun getImage(name: String): ImageResult = client.get("${baseUrl}breed/$name/images/random")

}