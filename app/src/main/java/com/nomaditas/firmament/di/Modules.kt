package com.nomaditas.firmament.di

import com.nomaditas.firmament.repository.Repository
import com.nomaditas.firmament.repository.RepositoryImpl
import com.nomaditas.firmament.ui.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl(androidContext()) }
    viewModel { MovieViewModel(get()) }
}
