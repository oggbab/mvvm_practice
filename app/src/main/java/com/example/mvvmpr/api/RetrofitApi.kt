package com.example.mvvmpr.api

import retrofit2.http.GET

interface RetrofitApi {
    @GET("googlecodelabs/kotlin-coroutines/master/advanced-coroutines-codelab/sunflower/src/main/assets/plants.json")
    suspend fun getAllPlant(): List<Plants>
}