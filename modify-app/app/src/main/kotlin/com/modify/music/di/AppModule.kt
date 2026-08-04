package com.modify.music.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.modify.music.data.database.ModifyDatabase
import com.modify.music.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "modify_preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ModifyDatabase {
        return Room.databaseBuilder(
            context,
            ModifyDatabase::class.java,
            ModifyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSongDao(database: ModifyDatabase) = database.songDao()

    @Provides
    @Singleton
    fun provideAlbumDao(database: ModifyDatabase) = database.albumDao()

    @Provides
    @Singleton
    fun provideArtistDao(database: ModifyDatabase) = database.artistDao()

    @Provides
    @Singleton
    fun providePlaylistDao(database: ModifyDatabase) = database.playlistDao()

    @Provides
    @Singleton
    fun providePlaylistSongDao(database: ModifyDatabase) = database.playlistSongDao()

    @Provides
    @Singleton
    fun provideSearchHistoryDao(database: ModifyDatabase) = database.searchHistoryDao()

    @Provides
    @Singleton
    fun providePlayHistoryDao(database: ModifyDatabase) = database.playHistoryDao()

    @Provides
    @Singleton
    fun provideDownloadDao(database: ModifyDatabase) = database.downloadDao()

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideSongRepository(dao: SongDao): SongRepository {
        return SongRepository(dao)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(dao: AlbumDao): AlbumRepository {
        return AlbumRepository(dao)
    }

    @Provides
    @Singleton
    fun provideArtistRepository(dao: ArtistDao): ArtistRepository {
        return ArtistRepository(dao)
    }

    @Provides
    @Singleton
    fun providePlaylistRepository(
        playlistDao: PlaylistDao,
        playlistSongDao: PlaylistSongDao
    ): PlaylistRepository {
        return PlaylistRepository(playlistDao, playlistSongDao)
    }

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(dao: SearchHistoryDao): SearchHistoryRepository {
        return SearchHistoryRepository(dao)
    }

    @Provides
    @Singleton
    fun providePlayHistoryRepository(dao: PlayHistoryDao): PlayHistoryRepository {
        return PlayHistoryRepository(dao)
    }

    @Provides
    @Singleton
    fun provideDownloadRepository(dao: DownloadDao): DownloadRepository {
        return DownloadRepository(dao)
    }
}
