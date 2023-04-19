package com.nomaditas.firmament.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nomaditas.firmament.domain.Movie
import com.nomaditas.firmament.domain.Repository
import com.nomaditas.firmament.network.ApiResponse

class MovieViewModel(repository: Repository) : ViewModel() {
    private var movies = repository.getMovies()

    fun getMovies(): MutableLiveData<List<Movie>> {
        return movies
    }
}
