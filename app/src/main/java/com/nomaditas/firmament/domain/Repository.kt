package com.nomaditas.firmament.domain

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.network.ApiResponse

interface Repository {
    fun getMovies(): MutableLiveData<List<Movie>>
}