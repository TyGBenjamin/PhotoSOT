package com.example.photosoftruth.data.remote.dto

import com.example.photosoftruth.data.local.entity.Photo

/**
 * Photo data transfer object retrieved from get request.
 *
 * @property albumId
 * @property id
 * @property thumbnailUrl
 * @property title
 * @property url
 * @constructor Create empty Photo d t o
 */
data class PhotoDTO(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
){
    /**
     * function to map PhotoDTO To Photo.
     *
     * @return
     */
    fun toPhoto():Photo{
        return Photo(
            id = this.id,
            albumId = this.albumId,
            thumbnailUrl = this.thumbnailUrl,
            title = this.title,
            url = this.url
        )
    }
}
