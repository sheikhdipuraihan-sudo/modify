package com.modify.music.data.repository

import com.modify.music.data.dao.AlbumDao
import com.modify.music.data.model.AlbumEntity
import kotlinx.coroutines.flow.Flow

class AlbumRepository(private val dao: AlbumDao) {
    val allAlbums: Flow<List<AlbumEntity>> = dao.getAllAlbums()
    fun getRecentAlbums(limit: Int = 20): Flow<List<AlbumEntity>> = dao.getRecentAlbums(limit)
    
    suspend fun getAlbumByBrowseId(browseId: String): AlbumEntity? = dao.getAlbumByBrowseId(browseId)
    suspend fun insertAlbum(album: AlbumEntity) = dao.insertAlbum(album)
    suspend fun insertAlbums(albums: List<AlbumEntity>) = dao.insertAlbums(albums)
    suspend fun deleteAlbum(album: AlbumEntity) = dao.deleteAlbum(album)
}
