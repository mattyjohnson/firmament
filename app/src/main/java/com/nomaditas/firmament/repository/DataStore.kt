package com.nomaditas.firmament.repository

interface DataStore {
    suspend fun isCacheValid(): Boolean
    suspend fun updateCacheTime()
}
