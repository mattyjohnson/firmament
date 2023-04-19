package com.nomaditas.firmament.domain

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.network.ApiResponse
import com.nomaditas.firmament.network.ApiServiceFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl : Repository {
    private var movies = MutableLiveData<List<ApiResponse>>()

    override fun getMovies(): MutableLiveData<List<ApiResponse>> {
        return movies
    }

    init {
        val service = ApiServiceFactory.create()
        val call = service.getMovies()
        call.enqueue(object : Callback<List<ApiResponse>> {
            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                movies.value = response.body()
            }
        })
    }
}
