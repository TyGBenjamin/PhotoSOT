package com.example.photosoftruth.data.prefs

import kotlinx.coroutines.flow.Flow

/**
 * Time pref
 *
 * @constructor Create empty Time pref
 */
interface TimePref {
    val savedTime: Flow<Long>
    suspend fun saveTimeStamp(savedTime: Long)
    suspend fun isdDataStale(currentTimeStamp: Long): Boolean
}
