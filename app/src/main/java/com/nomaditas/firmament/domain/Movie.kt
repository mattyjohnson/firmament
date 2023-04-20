package com.nomaditas.firmament.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val genre: String,
    val year: String,
    val plot: String,
    val poster: String,
) : Parcelable
