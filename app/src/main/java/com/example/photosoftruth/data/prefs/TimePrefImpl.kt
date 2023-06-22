package com.example.photosoftruth.data.prefs

import androidx.datastore.preferences.core.edit
import com.example.photosoftruth.utils.TimeDataStore.Companion.TIME_KEY
import com.example.photosoftruth.utils.TimeStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Time pref impl
 *
 * @property store
 * @constructor Create empty Time pref impl
 */
class TimePrefImpl @Inject constructor(private val store: TimeStore) : TimePref {
    override val savedTime: Flow<Long> get() = store.data.map { pref -> pref[TIME_KEY] ?: 0L }


    override suspend fun isdDataStale(currentTimeStamp: Long): Boolean {
        val lastUpdatedTimeStamp = savedTime.first()
        val timePassedSinceLastUpdate = currentTimeStamp - lastUpdatedTimeStamp
        return timePassedSinceLastUpdate > MAX_AGE
    }

    override suspend fun saveTimeStamp(savedTime: Long) {
        store.edit { preferences ->
            preferences[TIME_KEY] = savedTime
        }
    }

    companion object {
        private const val MAX_AGE = 60_000L
    }
}
