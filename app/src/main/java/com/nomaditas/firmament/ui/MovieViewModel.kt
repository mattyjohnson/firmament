package com.nomaditas.firmament.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomaditas.firmament.domain.Movie
import com.nomaditas.firmament.repository.Repository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: Repository) : ViewModel() {
    init {
        viewModelScope.launch {
            repository.loadMovies()
        }
    }

    fun getMovies(): MutableLiveData<List<Movie>> {
        return repository.getMovies()
    }

    fun getMovie(title: String): Movie? {
        return repository.getMovies().value?.find { it.title == title }
    }
}
