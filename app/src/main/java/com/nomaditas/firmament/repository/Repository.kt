package com.nomaditas.firmament.repository

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.domain.Movie

interface Repository {
    fun getMovies(): MutableLiveData<List<Movie>>
}