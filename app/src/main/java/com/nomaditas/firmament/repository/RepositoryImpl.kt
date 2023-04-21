package com.nomaditas.firmament.repository

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.domain.Movie
import com.nomaditas.firmament.network.ApiResponse
import com.nomaditas.firmament.network.ApiServiceFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl() : Repository, KoinComponent {
    private var movies = MutableLiveData<List<Movie>>()

    private val dataStore: DataStore by inject()

    override suspend fun loadMovies() {
        if (dataStore.isCacheValid()) {
        } else {
            refresh()
            dataStore.updateCacheTime()
        }
    }

    override fun getMovies(): MutableLiveData<List<Movie>> {
        return movies
    }

    private fun refresh() {
        val service = ApiServiceFactory.create()
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
