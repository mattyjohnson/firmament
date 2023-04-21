package com.nomaditas.firmament.repository

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.domain.Movie

interface Repository {
    suspend fun loadMovies()
    fun getMovies(): MutableLiveData<List<Movie>>
}
