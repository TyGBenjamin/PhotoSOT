package com.example.photosoftruth.utils

/**
 * Resource.
 *
 * @param T
 * @constructor Create empty Resource
 */
sealed class Resource<out T> {
    /**
     * Success.
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<T>( val data:T):Resource<T>()

    /**
     * Idle.
     *
     * @constructor Create empty Idle
     */
    object Idle: Resource<Nothing>()

    /**
     * Loading.
     *
     * @constructor Create empty Loading
     */
    object Loading: Resource<Nothing>()

    /**
     * Error.
     *
     * @property errorMessage
     * @constructor Create empty Error
     */
    data class Error(val errorMessage: String): Resource<Nothing>()
}
