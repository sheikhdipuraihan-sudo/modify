package com.modify.music.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.modify.music.ui.components.FeaturedCard
import com.modify.music.ui.components.ShimmerSongCard
import com.modify.music.ui.components.SongCard
import com.modify.music.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSongClick: (String) -> Unit,
    onAlbumClick: (String) -> Unit,
    onArtistClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Modify",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Featured Section
            if (!uiState.isLoading) {
                item {
                    FeaturedCard(
                        title = "Discover Weekly",
                        subtitle = "Your personalized playlist",
                        imageUrl = null,
                        onClick = { },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // Recently Played Section
            item {
                Text(
                    text = "Recently Played",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            if (uiState.isLoading) {
                items(5) { index ->
                    ShimmerSongCard(
                        modifier = Modifier
                            .fillParentMaxWidth(0.25f)
                            .padding(4.dp)
                    )
                }
            } else {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(uiState.recentlyPlayed) { song ->
                            SongCard(
                                song = song,
                                onClick = { onSongClick(song.videoId) },
                                modifier = Modifier.width(140.dp)
                            )
                        }
                    }
                }
            }

            // Trending Section
            item {
                Text(
                    text = "Trending Now",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (uiState.isLoading) {
                items(5) { index ->
                    ShimmerSongCard(
                        modifier = Modifier
                            .fillParentMaxWidth(0.25f)
                            .padding(4.dp)
                    )
                }
            } else {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(uiState.trendingSongs) { song ->
                            SongCard(
                                song = song,
                                onClick = { onSongClick(song.videoId) },
                                modifier = Modifier.width(140.dp)
                            )
                        }
                    }
                }
            }

            // New Releases Section
            item {
                Text(
                    text = "New Releases",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            if (uiState.isLoading) {
                items(5) { index ->
                    ShimmerSongCard(
                        modifier = Modifier
                            .fillParentMaxWidth(0.25f)
                            .padding(4.dp)
                    )
                }
            } else {
                item {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp)
                    ) {
                        items(uiState.newReleases) { song ->
                            SongCard(
                                song = song,
                                onClick = { onSongClick(song.videoId) },
                                modifier = Modifier.width(140.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
