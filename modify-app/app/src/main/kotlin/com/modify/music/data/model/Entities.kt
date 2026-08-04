package com.modify.music.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Domain models (used in UI)
data class Song(
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

data class Album(
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

data class Artist(
    val browseId: String,
    val name: String,
    val thumbnailUrl: String?,
    val subscriberCount: String?,
    val isSubscribed: Boolean = false,
    val addedAt: Long = System.currentTimeMillis()
)

data class Playlist(
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

data class PlaylistSong(
    val id: Long = 0,
    val playlistId: String,
    val videoId: String,
    val position: Int,
    val addedAt: Long = System.currentTimeMillis()
)

data class SearchHistory(
    val id: Long = 0,
    val query: String,
    val searchedAt: Long = System.currentTimeMillis()
)

data class PlayHistory(
    val id: Long = 0,
    val videoId: String,
    val title: String,
    val artist: String,
    val thumbnailUrl: String?,
    val playedAt: Long = System.currentTimeMillis(),
    val position: Int = 0
)

data class Download(
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

// Entity conversions
fun Song.toEntity() = SongEntity(
    videoId = videoId,
    title = title,
    artist = artist,
    artistId = artistId,
    album = album,
    albumId = albumId,
    duration = duration,
    thumbnailUrl = thumbnailUrl,
    isExplicit = isExplicit,
    isDownloaded = isDownloaded,
    downloadPath = downloadPath,
    lastPlayedAt = lastPlayedAt,
    playCount = playCount,
    isLiked = isLiked,
    addedAt = addedAt
)

fun SongEntity.toDomain() = Song(
    videoId = videoId,
    title = title,
    artist = artist,
    artistId = artistId,
    album = album,
    albumId = albumId,
    duration = duration,
    thumbnailUrl = thumbnailUrl,
    isExplicit = isExplicit,
    isDownloaded = isDownloaded,
    downloadPath = downloadPath,
    lastPlayedAt = lastPlayedAt,
    playCount = playCount,
    isLiked = isLiked,
    addedAt = addedAt
)

fun Album.toEntity() = AlbumEntity(
    browseId = browseId,
    title = title,
    artist = artist,
    artistId = artistId,
    year = year,
    thumbnailUrl = thumbnailUrl,
    isExplicit = isExplicit,
    songCount = songCount,
    addedAt = addedAt
)

fun AlbumEntity.toDomain() = Album(
    browseId = browseId,
    title = title,
    artist = artist,
    artistId = artistId,
    year = year,
    thumbnailUrl = thumbnailUrl,
    isExplicit = isExplicit,
    songCount = songCount,
    addedAt = addedAt
)

fun Artist.toEntity() = ArtistEntity(
    browseId = browseId,
    name = name,
    thumbnailUrl = thumbnailUrl,
    subscriberCount = subscriberCount,
    isSubscribed = isSubscribed,
    addedAt = addedAt
)

fun ArtistEntity.toDomain() = Artist(
    browseId = browseId,
    name = name,
    thumbnailUrl = thumbnailUrl,
    subscriberCount = subscriberCount,
    isSubscribed = isSubscribed,
    addedAt = addedAt
)

fun Playlist.toEntity() = PlaylistEntity(
    playlistId = playlistId,
    title = title,
    description = description,
    thumbnailUrl = thumbnailUrl,
    authorName = authorName,
    authorId = authorId,
    songCount = songCount,
    isLocal = isLocal,
    createdAt = createdAt
)

fun PlaylistEntity.toDomain() = Playlist(
    playlistId = playlistId,
    title = title,
    description = description,
    thumbnailUrl = thumbnailUrl,
    authorName = authorName,
    authorId = authorId,
    songCount = songCount,
    isLocal = isLocal,
    createdAt = createdAt
)

fun PlaylistSong.toEntity() = PlaylistSongEntity(
    id = id,
    playlistId = playlistId,
    videoId = videoId,
    position = position,
    addedAt = addedAt
)

fun PlaylistSongEntity.toDomain() = PlaylistSong(
    id = id,
    playlistId = playlistId,
    videoId = videoId,
    position = position,
    addedAt = addedAt
)

fun SearchHistory.toEntity() = SearchHistoryEntity(
    id = id,
    query = query,
    searchedAt = searchedAt
)

fun SearchHistoryEntity.toDomain() = SearchHistory(
    id = id,
    query = query,
    searchedAt = searchedAt
)

fun PlayHistory.toEntity() = PlayHistoryEntity(
    id = id,
    videoId = videoId,
    title = title,
    artist = artist,
    thumbnailUrl = thumbnailUrl,
    playedAt = playedAt,
    position = position
)

fun PlayHistoryEntity.toDomain() = PlayHistory(
    id = id,
    videoId = videoId,
    title = title,
    artist = artist,
    thumbnailUrl = thumbnailUrl,
    playedAt = playedAt,
    position = position
)

fun Download.toEntity() = DownloadEntity(
    videoId = videoId,
    title = title,
    artist = artist,
    album = album,
    thumbnailUrl = thumbnailUrl,
    filePath = filePath,
    fileSize = fileSize,
    quality = quality,
    downloadedAt = downloadedAt
)

fun DownloadEntity.toDomain() = Download(
    videoId = videoId,
    title = title,
    artist = artist,
    album = album,
    thumbnailUrl = thumbnailUrl,
    filePath = filePath,
    fileSize = fileSize,
    quality = quality,
    downloadedAt = downloadedAt
)
