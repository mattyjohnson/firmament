package com.nomaditas.firmament.network

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/759fdfa82d6f33522e11")
    fun getMovies(): Call<List<ApiResponse>>
}
