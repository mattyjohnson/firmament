package com.nomaditas.firmament.repository

import androidx.datastore.core.Serializer
import com.nomaditas.firmament.domain.Movie
import java.io.InputStream
import java.io.OutputStream

class MoviesSerializer : Serializer<Movie> {
    override val defaultValue: Movie
        get() = TODO("Not yet implemented")

    override suspend fun readFrom(input: InputStream): Movie {
        TODO("Not yet implemented")
    }

    override suspend fun writeTo(t: Movie, output: OutputStream) {
        TODO("Not yet implemented")
    }
}