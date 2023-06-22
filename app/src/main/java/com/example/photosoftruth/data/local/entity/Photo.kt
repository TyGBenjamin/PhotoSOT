package com.example.photosoftruth.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Photo.
 *
 *
 * @property albumId
 * @property id
 * @property thumbnailUrl
 * @property title
 * @property url
 * @constructor Create empty Photo
 */

@Entity(tableName = "photos")
data class Photo(
    @ColumnInfo
    val albumId: Int,
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    val thumbnailUrl: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val url: String
)
