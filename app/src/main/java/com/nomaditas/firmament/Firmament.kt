package com.nomaditas.firmament

import android.app.Application
import com.nomaditas.firmament.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Firmament : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Firmament)
            modules(appModule)
        }
    }
}
