package com.modify.music.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.modify.music.data.model.Song
import com.modify.music.data.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val trendingSongs: List<Song> = emptyList(),
    val recentlyPlayed: List<Song> = emptyList(),
    val recommendedSongs: List<Song> = emptyList(),
    val newReleases: List<Song> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val songRepository: SongRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                
                // Load recently played songs
                songRepository.getRecentlyPlayed(20).collect { songs ->
                    _uiState.update { 
                        it.copy(
                            recentlyPlayed = songs,
                            isLoading = false
                        ) 
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Failed to load home data"
                    ) 
                }
            }
        }
    }

    fun refresh() {
        loadHomeData()
    }
}
