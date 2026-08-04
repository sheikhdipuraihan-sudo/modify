package com.modify.music.data.repository

import com.modify.music.data.dao.ArtistDao
import com.modify.music.data.model.ArtistEntity
import kotlinx.coroutines.flow.Flow

class ArtistRepository(private val dao: ArtistDao) {
    val allArtists: Flow<List<ArtistEntity>> = dao.getAllArtists()
    val subscribedArtists: Flow<List<ArtistEntity>> = dao.getSubscribedArtists()
    
    suspend fun getArtistByBrowseId(browseId: String): ArtistEntity? = dao.getArtistByBrowseId(browseId)
    suspend fun insertArtist(artist: ArtistEntity) = dao.insertArtist(artist)
    suspend fun insertArtists(artists: List<ArtistEntity>) = dao.insertArtists(artists)
    suspend fun setSubscribed(browseId: String, isSubscribed: Boolean) = dao.setSubscribed(browseId, isSubscribed)
    suspend fun deleteArtist(artist: ArtistEntity) = dao.deleteArtist(artist)
}
