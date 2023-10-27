package com.example.mvvmpr.api

class Repository {
    private val client = RetrofitInstance.getInstance().create(RetrofitApi::class.java)
    suspend fun getAllData() = client.getAllPlant()
}