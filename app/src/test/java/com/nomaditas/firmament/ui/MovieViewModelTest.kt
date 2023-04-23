package com.nomaditas.firmament.ui

import androidx.lifecycle.MutableLiveData
import com.nomaditas.firmament.domain.Movie
import com.nomaditas.firmament.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    @MockK
    lateinit var repository: Repository

    lateinit var sut: MovieViewModel

    @Before
    fun doBefore() {
        MockKAnnotations.init(this)

        every { repository.getMovies() } returns MutableLiveData<List<Movie>>(MOVIES)
        sut = MovieViewModel(repository)
    }

    @Test
    fun test_get_movie() {
        sut.getMovies()

        val result1 = sut.getMovie(MOVIE_TITLE_1)
        assertEquals(MOVIE_1, result1)

        val result2 = sut.getMovie(MOVIE_TITLE_2)
        assertEquals(MOVIE_2, result2)

        val result3 = sut.getMovie(MOVIE_TITLE_3)
        assertEquals(MOVIE_3, result3)
    }

    companion object {
        private const val MOVIE_TITLE_1 = "title1"
        private const val MOVIE_TITLE_2 = "title2"
        private const val MOVIE_TITLE_3 = "title3"
        private val MOVIE_1 = Movie(MOVIE_TITLE_1, "genre", "year", "plot", "poster")
        private val MOVIE_2 = Movie(MOVIE_TITLE_2, "genre", "year", "plot", "poster")
        private val MOVIE_3 = Movie(MOVIE_TITLE_3, "genre", "year", "plot", "poster")
        private val MOVIES = listOf(MOVIE_1, MOVIE_2, MOVIE_3)
    }
}
