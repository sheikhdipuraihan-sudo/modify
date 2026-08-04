package com.modify.music.data.repository

import com.modify.music.data.dao.SongDao
import com.modify.music.data.model.Song
import com.modify.music.data.model.SongEntity
import com.modify.music.data.model.toDomain
import com.modify.music.data.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SongRepository(private val dao: SongDao) {
    val allSongs: Flow<List<Song>> = dao.getAllSongs().map { it.map { entity -> entity.toDomain() } }
    val likedSongs: Flow<List<Song>> = dao.getLikedSongs().map { it.map { entity -> entity.toDomain() } }
    
    fun getRecentlyPlayed(limit: Int = 50): Flow<List<Song>> = 
        dao.getRecentlyPlayed(limit).map { it.map { entity -> entity.toDomain() } }
    
    fun getSongsByAlbum(albumId: String): Flow<List<Song>> = 
        dao.getSongsByAlbum(albumId).map { it.map { entity -> entity.toDomain() } }
    
    fun getSongsByArtist(artistId: String): Flow<List<Song>> = 
        dao.getSongsByArtist(artistId).map { it.map { entity -> entity.toDomain() } }
    
    fun searchSongs(query: String, limit: Int = 50): Flow<List<Song>> = 
        dao.searchSongs(query, limit).map { it.map { entity -> entity.toDomain() } }

    suspend fun getSongByVideoId(videoId: String): Song? = 
        dao.getSongByVideoId(videoId)?.toDomain()
    
    fun getSongByVideoIdFlow(videoId: String): Flow<Song?> = 
        dao.getSongByVideoIdFlow(videoId).map { it?.toDomain() }

    suspend fun insertSong(song: Song) = dao.insertSong(song.toEntity())
    suspend fun insertSongs(songs: List<Song>) = dao.insertSongs(songs.map { it.toEntity() })
    suspend fun updateSong(song: Song) = dao.updateSong(song.toEntity())
    
    suspend fun incrementPlayCount(videoId: String, timestamp: Long = System.currentTimeMillis()) =
        dao.incrementPlayCount(videoId, timestamp)
    
    suspend fun setLiked(videoId: String, isLiked: Boolean) = dao.setLiked(videoId, isLiked)
    suspend fun deleteSong(videoId: String) = dao.deleteSong(videoId)
}
