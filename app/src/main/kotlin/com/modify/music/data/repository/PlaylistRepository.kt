package com.modify.music.data.repository

import com.modify.music.data.dao.PlaylistDao
import com.modify.music.data.dao.PlaylistSongDao
import com.modify.music.data.model.PlaylistEntity
import com.modify.music.data.model.PlaylistSongEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class PlaylistRepository(
    private val playlistDao: PlaylistDao,
    private val playlistSongDao: PlaylistSongDao
) {
    val allPlaylists: Flow<List<PlaylistEntity>> = playlistDao.getAllPlaylists()
    val localPlaylists: Flow<List<PlaylistEntity>> = playlistDao.getLocalPlaylists()
    
    suspend fun getPlaylistById(playlistId: String): PlaylistEntity? = playlistDao.getPlaylistById(playlistId)
    fun getPlaylistSongs(playlistId: String): Flow<List<PlaylistSongEntity>> = playlistSongDao.getPlaylistSongs(playlistId)
    
    suspend fun createPlaylist(title: String, description: String? = null): String {
        val id = "local_${UUID.randomUUID().toString().take(8)}"
        val playlist = PlaylistEntity(
            playlistId = id,
            title = title,
            description = description,
            thumbnailUrl = null,
            authorName = null,
            authorId = null,
            songCount = 0,
            isLocal = true
        )
        playlistDao.insertPlaylist(playlist)
        return id
    }
    
    suspend fun updatePlaylistTitle(playlistId: String, title: String) {
        val playlist = playlistDao.getPlaylistById(playlistId) ?: return
        playlistDao.updatePlaylist(playlist.copy(title = title))
    }
    
    suspend fun deletePlaylist(playlistId: String) {
        playlistDao.clearPlaylist(playlistId)
        playlistDao.deletePlaylistById(playlistId)
    }
    
    suspend fun addSongToPlaylist(playlistId: String, videoId: String, position: Int) {
        val playlistSong = PlaylistSongEntity(
            playlistId = playlistId,
            videoId = videoId,
            position = position
        )
        playlistSongDao.insertPlaylistSong(playlistSong)
        
        // Update song count
        val playlist = playlistDao.getPlaylistById(playlistId) ?: return
        val currentCount = playlistSongDao.getPlaylistSongs(playlistId)
            .firstOrNull()?.size ?: 0
        playlistDao.updatePlaylist(playlist.copy(songCount = currentCount + 1))
    }
    
    suspend fun removeSongFromPlaylist(playlistId: String, videoId: String) {
        playlistSongDao.removeSongFromPlaylist(playlistId, videoId)
        
        // Update song count
        val playlist = playlistDao.getPlaylistById(playlistId) ?: return
        val currentCount = playlistSongDao.getPlaylistSongs(playlistId)
            .firstOrNull()?.size ?: 0
        playlistDao.updatePlaylist(playlist.copy(songCount = currentCount))
    }
    
    suspend fun reorderPlaylistSong(id: Long, newPosition: Int) {
        playlistSongDao.updatePosition(id, newPosition)
    }
}
