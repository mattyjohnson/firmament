package com.nomaditas.firmament.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.util.*

private val Context.dataStore by preferencesDataStore(
    name = "PreferenceDataStore",
)

class DataStoreImpl(context: Context) : DataStore {
    private val dataStore = context.dataStore

    override suspend fun isCacheValid(): Boolean {
        val time = Calendar.getInstance().timeInMillis
        return (time - getCacheTime()) < CACHE_TIME
    }

    private suspend fun getCacheTime() = dataStore.data.first()[cacheTimeKey] ?: 0

    override suspend fun updateCacheTime() {
        dataStore.edit { preferences ->
            preferences[cacheTimeKey] = Calendar.getInstance().timeInMillis
        }
    }

    companion object {
        val cacheTimeKey = longPreferencesKey("CACHE_TIME_KEY")
        private const val CACHE_TIME = 10 * 60 * 1000 // ten minutes in milliseconds
    }
}
