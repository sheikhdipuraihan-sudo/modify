package com.modify.music.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.modify.music.data.model.*

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY addedAt DESC")
    fun getAllSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE isLiked = 1 ORDER BY addedAt DESC")
    fun getLikedSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE videoId = :videoId")
    suspend fun getSongByVideoId(videoId: String): SongEntity?

    @Query("SELECT * FROM songs WHERE videoId = :videoId")
    fun getSongByVideoIdFlow(videoId: String): Flow<SongEntity?>

    @Query("SELECT * FROM songs ORDER BY lastPlayedAt DESC LIMIT :limit")
    fun getRecentlyPlayed(limit: Int = 50): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE albumId = :albumId ORDER BY title")
    fun getSongsByAlbum(albumId: String): Flow<List<SongEntity>>

    @Query("SELECT * FROM songs WHERE artistId = :artistId ORDER BY title")
    fun getSongsByArtist(artistId: String): Flow<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Update
    suspend fun updateSong(song: SongEntity)

    @Query("UPDATE songs SET playCount = playCount + 1, lastPlayedAt = :timestamp WHERE videoId = :videoId")
    suspend fun incrementPlayCount(videoId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE songs SET isLiked = :isLiked WHERE videoId = :videoId")
    suspend fun setLiked(videoId: String, isLiked: Boolean)

    @Query("DELETE FROM songs WHERE videoId = :videoId")
    suspend fun deleteSong(videoId: String)

    @Query("SELECT * FROM songs WHERE title LIKE '%' || :query || '%' OR artist LIKE '%' || :query || '%' ORDER BY playCount DESC LIMIT :limit")
    fun searchSongs(query: String, limit: Int = 50): Flow<List<SongEntity>>
}

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY addedAt DESC")
    fun getAllAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM albums WHERE browseId = :browseId")
    suspend fun getAlbumByBrowseId(browseId: String): AlbumEntity?

    @Query("SELECT * FROM albums ORDER BY addedAt DESC LIMIT :limit")
    fun getRecentAlbums(limit: Int = 20): Flow<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)

    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)
}

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists ORDER BY name")
    fun getAllArtists(): Flow<List<ArtistEntity>>

    @Query("SELECT * FROM artists WHERE browseId = :browseId")
    suspend fun getArtistByBrowseId(browseId: String): ArtistEntity?

    @Query("SELECT * FROM artists WHERE isSubscribed = 1 ORDER BY name")
    fun getSubscribedArtists(): Flow<List<ArtistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<ArtistEntity>)

    @Query("UPDATE artists SET isSubscribed = :isSubscribed WHERE browseId = :browseId")
    suspend fun setSubscribed(browseId: String, isSubscribed: Boolean)

    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)
}

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlists ORDER BY createdAt DESC")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>

    @Query("SELECT * FROM playlists WHERE playlistId = :playlistId")
    suspend fun getPlaylistById(playlistId: String): PlaylistEntity?

    @Query("SELECT * FROM playlists WHERE isLocal = 1 ORDER BY createdAt DESC")
    fun getLocalPlaylists(): Flow<List<PlaylistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)

    @Update
    suspend fun updatePlaylist(playlist: PlaylistEntity)

    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)

    @Query("DELETE FROM playlists WHERE playlistId = :playlistId")
    suspend fun deletePlaylistById(playlistId: String)
}

@Dao
interface PlaylistSongDao {
    @Query("SELECT * FROM playlist_songs WHERE playlistId = :playlistId ORDER BY position")
    fun getPlaylistSongs(playlistId: String): Flow<List<PlaylistSongEntity>>

    @Query("SELECT ps.*, s.title, s.artist, s.thumbnailUrl, s.duration FROM playlist_songs ps JOIN songs s ON ps.videoId = s.videoId WHERE ps.playlistId = :playlistId ORDER BY ps.position")
    fun getPlaylistSongsWithDetails(playlistId: String): Flow<List<PlaylistSongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistSong(playlistSong: PlaylistSongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylistSongs(playlistSongs: List<PlaylistSongEntity>)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND videoId = :videoId")
    suspend fun removeSongFromPlaylist(playlistId: String, videoId: String)

    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId")
    suspend fun clearPlaylist(playlistId: String)

    @Query("UPDATE playlist_songs SET position = :position WHERE id = :id")
    suspend fun updatePosition(id: Long, position: Int)
}

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY searchedAt DESC LIMIT :limit")
    fun getRecentSearches(limit: Int = 20): Flow<List<SearchHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(history: SearchHistoryEntity)

    @Query("DELETE FROM search_history WHERE query = :query")
    suspend fun deleteSearchQuery(query: String)

    @Query("DELETE FROM search_history")
    suspend fun clearAllHistory()
}

@Dao
interface PlayHistoryDao {
    @Query("SELECT * FROM play_history ORDER BY playedAt DESC LIMIT :limit")
    fun getRecentPlayHistory(limit: Int = 50): Flow<List<PlayHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayHistory(history: PlayHistoryEntity)

    @Query("DELETE FROM play_history")
    suspend fun clearAllHistory()
}

@Dao
interface DownloadDao {
    @Query("SELECT * FROM downloads ORDER BY downloadedAt DESC")
    fun getAllDownloads(): Flow<List<DownloadEntity>>

    @Query("SELECT * FROM downloads WHERE videoId = :videoId")
    suspend fun getDownloadByVideoId(videoId: String): DownloadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(download: DownloadEntity)

    @Delete
    suspend fun deleteDownload(download: DownloadEntity)

    @Query("DELETE FROM downloads WHERE videoId = :videoId")
    suspend fun deleteDownloadByVideoId(videoId: String)

    @Query("SELECT SUM(fileSize) FROM downloads")
    suspend fun getTotalDownloadSize(): Long?
}
