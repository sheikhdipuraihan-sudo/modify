package com.modify.music.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val videoId: String,
    val title: String,
    val artist: String,
    val artistId: String?,
    val album: String?,
    val albumId: String?,
    val duration: Int,
    val thumbnailUrl: String?,
    val isExplicit: Boolean = false,
    val isDownloaded: Boolean = false,
    val downloadPath: String? = null,
    val lastPlayedAt: Long? = null,
    val playCount: Int = 0,
    val isLiked: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey
    val browseId: String,
    val title: String,
    val artist: String,
    val artistId: String?,
    val year: String?,
    val thumbnailUrl: String?,
    val isExplicit: Boolean = false,
    val songCount: Int = 0,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "artists")
data class ArtistEntity(
    @PrimaryKey
    val browseId: String,
    val name: String,
    val thumbnailUrl: String?,
    val subscriberCount: String?,
    val isSubscribed: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey
    val playlistId: String,
    val title: String,
    val description: String?,
    val thumbnailUrl: String?,
    val authorName: String?,
    val authorId: String?,
    val songCount: Int = 0,
    val isLocal: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "playlist_songs")
data class PlaylistSongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val playlistId: String,
    val videoId: String,
    val position: Int,
    val addedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val query: String,
    val searchedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "play_history")
data class PlayHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val videoId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String?,
    val playedAt: Long = System.currentTimeMillis(),
    val position: Int = 0
)

@Entity(tableName = "downloads")
data class DownloadEntity(
    @PrimaryKey
    val videoId: String,
    val title: String,
    val artist: String,
    val album: String?,
    val thumbnailUrl: String?,
    val filePath: String,
    val fileSize: Long,
    val quality: String,
    val downloadedAt: Long = System.currentTimeMillis()
)
