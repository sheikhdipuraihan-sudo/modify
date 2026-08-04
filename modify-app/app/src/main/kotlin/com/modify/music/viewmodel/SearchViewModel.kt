package com.modify.music.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modify.music.data.model.Song
import com.modify.music.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val searchResults: List<Song> = emptyList(),
    val searchHistory: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        loadSearchHistory()
    }

    private fun loadSearchHistory() {
        viewModelScope.launch {
            searchRepository.getSearchHistory().collect { history ->
                _uiState.update { it.copy(searchHistory = history.map { h -> h.query }) }
            }
        }
    }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300) // Debounce
            if (query.isNotBlank()) {
                search(query)
            }
        }
    }

    private suspend fun search(query: String) {
        try {
            _uiState.update { it.copy(isLoading = true) }
            
            searchRepository.searchSongs(query).collect { songs ->
                _uiState.update { 
                    it.copy(
                        searchResults = songs,
                        isLoading = false
                    ) 
                }
            }
        } catch (e: Exception) {
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    error = e.message ?: "Search failed"
                ) 
            }
        }
    }

    fun addToHistory(query: String) {
        viewModelScope.launch {
            searchRepository.addToSearchHistory(query)
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            searchRepository.clearSearchHistory()
        }
    }

    fun removeHistoryItem(query: String) {
        viewModelScope.launch {
            searchRepository.removeFromSearchHistory(query)
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
