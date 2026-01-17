package com.spotifraud.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spotifraud.app.ui.library.LibraryScreen
import com.spotifraud.app.ui.player.PlayerScreen
import com.spotifraud.app.ui.player.PlayerViewModel
import com.spotifraud.app.ui.search.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    playerViewModel: PlayerViewModel
) {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("search") }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == "search",
                    onClick = { 
                        currentRoute = "search"
                        navController.navigate("search")
                    },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = currentRoute == "library",
                    onClick = { 
                        currentRoute = "library"
                        navController.navigate("library")
                    },
                    icon = { Icon(Icons.Default.List, contentDescription = "Library") },
                    label = { Text("Library") }
                )
                NavigationBarItem(
                    selected = currentRoute == "player",
                    onClick = { 
                        currentRoute = "player"
                        navController.navigate("player")
                    },
                    icon = { Icon(Icons.Default.PlayArrow, contentDescription = "Player") },
                    label = { Text("Player") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "search",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("search") {
                SearchScreen(
                    onResultClick = { result ->
                        playerViewModel.playMedia(result.id, result.title, result.artist, result.streamUrl ?: "")
                        navController.navigate("player")
                    }
                )
            }
            composable("library") {
                LibraryScreen(
                    downloads = emptyList(), // Provide from ViewModel later
                    onItemClick = { result ->
                        playerViewModel.playMedia(result.id, result.title, result.artist, result.streamUrl ?: "")
                        navController.navigate("player")
                    }
                )
            }
            composable("player") {
                val isPlaying by playerViewModel.isPlaying.collectAsState()
                val currentItem by playerViewModel.currentMediaItem.collectAsState()
                
                PlayerScreen(
                    currentMediaItem = currentItem,
                    isPlaying = isPlaying,
                    onPlayPause = { playerViewModel.playPause() },
                    onNext = {},
                    onPrevious = {}
                )
            }
        }
    }
}
