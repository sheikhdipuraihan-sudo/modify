package com.modify.music.data.repository

import com.modify.music.data.dao.SearchHistoryDao
import com.modify.music.data.dao.SongDao
import com.modify.music.data.model.SearchHistory
import com.modify.music.data.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val songDao: SongDao,
    private val historyDao: SearchHistoryDao
) {

    fun searchSongs(query: String) =
        songDao.searchSongs(query).map { list ->
            list.map { it.toDomain() }
        }

    fun getSearchHistory(): Flow<List<SearchHistory>> =
        historyDao.getRecentSearches().map { list ->
            list.map { it.toDomain() }
        }

    suspend fun addToSearchHistory(query: String) {
        historyDao.insertSearchHistory(
            com.modify.music.data.model.SearchHistoryEntity(
                query = query
            )
        )
    }

    suspend fun removeFromSearchHistory(query: String) {
        historyDao.deleteSearchQuery(query)
    }

    suspend fun clearSearchHistory() {
        historyDao.clearAllHistory()
    }
}
