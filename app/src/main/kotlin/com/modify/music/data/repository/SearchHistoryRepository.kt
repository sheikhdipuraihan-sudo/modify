package com.modify.music.data.repository

import com.modify.music.data.dao.SearchHistoryDao
import com.modify.music.data.model.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

class SearchHistoryRepository(private val dao: SearchHistoryDao) {
    fun getRecentSearches(limit: Int = 20): Flow<List<SearchHistoryEntity>> = dao.getRecentSearches(limit)
    
    suspend fun addSearchQuery(query: String) {
        dao.insertSearchHistory(SearchHistoryEntity(query = query))
    }
    
    suspend fun deleteSearchQuery(query: String) {
        dao.deleteSearchQuery(query)
    }
    
    suspend fun clearAllHistory() {
        dao.clearAllHistory()
    }
}
