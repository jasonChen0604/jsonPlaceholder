package com.example.json

class PhotoDataModel(
    albumId: String, id: String,
    title: String, url: String,
    thumbnailUrl: String
) {
    private val albumId: String
    private val id: String
    private val title: String
    private val url: String
    private val thumbnailUrl: String

    fun getAlbumId(): String {
        return albumId
    }

    fun getID(): String {
        return id
    }

    fun getTitle(): String {
        return title
    }

    fun getUrl(): String {
        return url
    }

    fun getThumbnailUrl(): String {
        return thumbnailUrl
    }

    init {
        this.albumId = albumId
        this.id = id
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
    }
}