package com.modify.music.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Library : Screen("library")
    object Settings : Screen("settings")
    object Player : Screen("player/{songId}") {
        fun createRoute(songId: String) = "player/$songId"
    }
    object Album : Screen("album/{albumId}") {
        fun createRoute(albumId: String) = "album/$albumId"
    }
    object Artist : Screen("artist/{artistId}") {
        fun createRoute(artistId: String) = "artist/$artistId"
    }
    object Playlist : Screen("playlist/{playlistId}") {
        fun createRoute(playlistId: String) = "playlist/$playlistId"
    }
    object Downloads : Screen("downloads")
    object CreatePlaylist : Screen("create_playlist")
    object EditPlaylist : Screen("edit_playlist/{playlistId}") {
        fun createRoute(playlistId: String) = "edit_playlist/$playlistId"
    }
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Library,
    Screen.Settings
)
