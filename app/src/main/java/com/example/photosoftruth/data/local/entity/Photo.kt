package com.example.photosoftruth.data.local.entity

/**
 * Photo.
 *
 * @property albumId
 * @property id
 * @property thumbnailUrl
 * @property title
 * @property url
 * @constructor Create empty Photo
 */
data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)
