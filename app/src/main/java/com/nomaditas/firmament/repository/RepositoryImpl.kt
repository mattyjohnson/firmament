package com.nomaditas.firmament.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.domain.Movie
import com.nomaditas.firmament.network.ApiResponse
import com.nomaditas.firmament.network.ApiServiceFactory
import org.koin.core.component.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(context: Context) : Repository, KoinComponent {
    private var movies = MutableLiveData<List<Movie>>()

    override fun getMovies(): MutableLiveData<List<Movie>> {
        return movies
    }

    init {
        val service = ApiServiceFactory.create(context)
        val call = service.getMovies()
        call.enqueue(object : Callback<List<ApiResponse>> {
            override fun onFailure(call: Call<List<ApiResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<ApiResponse>>, response: Response<List<ApiResponse>>) {
                movies.value = response.body()?.let {
                    mapToDomain(it)
                }
            }
        })
    }

    fun mapToDomain(data: List<ApiResponse>): List<Movie> {
        return data.map {
            Movie(it.Title, it.Genre, it.Year, it.Plot, it.Poster)
        }
    }
}
