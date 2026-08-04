package com.modify.music.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.modify.music.ui.navigation.Screen
import com.modify.music.ui.navigation.bottomNavItems
import com.modify.music.ui.screens.HomeScreen
import com.modify.music.ui.screens.LibraryScreen
import com.modify.music.ui.screens.SearchScreen
import com.modify.music.ui.screens.SettingsScreen
import com.modify.music.ui.theme.ModifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        
        super.onCreate(savedInstanceState)

        setContent {
            ModifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ModifyApp()
                }
            }
        }
    }
}

@Composable
fun ModifyApp() {
    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onSongClick = { songId -> /* Navigate to player */ },
                    onAlbumClick = { albumId -> /* Navigate to album */ },
                    onArtistClick = { artistId -> /* Navigate to artist */ }
                )
            }
            
            composable(Screen.Search.route) {
                SearchScreen(
                    onSongClick = { songId -> /* Navigate to player */ }
                )
            }
            
            composable(Screen.Library.route) {
                LibraryScreen(
                    onNavigate = { route -> navController.navigate(route) }
                )
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = getIconForScreen(screen),
                        contentDescription = screen.route
                    )
                },
                label = { Text(getLabelForScreen(screen)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Composable
fun getIconForScreen(screen: Screen): ImageVector {
    return when (screen) {
        Screen.Home -> Icons.Default.Home
        Screen.Search -> Icons.Default.Search
        Screen.Library -> Icons.Default.LibraryMusic
        Screen.Settings -> Icons.Default.Settings
        else -> Icons.Default.Home
    }
}

@Composable
fun getLabelForScreen(screen: Screen): String {
    return when (screen) {
        Screen.Home -> "Home"
        Screen.Search -> "Search"
        Screen.Library -> "Library"
        Screen.Settings -> "Settings"
        else -> "Home"
    }
}
