package com.example.photosoftruth.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


typealias TimeStore = DataStore<Preferences>

val Context.dataStore: TimeStore by preferencesDataStore(name = "time_preferences")

/**
 * Time data store.
 *
 * @property context
 * @constructor Create empty Time data store
 */
class TimeDataStore(val context: Context) {

//
//    /**
//     * Save time.
//     *
//     * @param savedTime
//     */
//    suspend fun saveTime(savedTime: Long) {
//        context.dataStore.edit { preferences ->
//            preferences[TIME_KEY] = savedTime
//        }
//    }
//
//    /**
//     * Saved time.
//     */
//    val savedTime: Flow<Long?> = context.dataStore.data.map { preferences ->
//        preferences[TIME_KEY]
//    }

    companion object {
        val TIME_KEY = longPreferencesKey("time_key")
    }

}
