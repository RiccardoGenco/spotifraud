package com.spotifraud.app.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spotifraud.app.domain.model.ExtractionResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onResultClick: (ExtractionResult) -> Unit
) {
    var query by remember { mutableStateOf("") }
    val mockResults = listOf(
        ExtractionResult("1", "Never Gonna Give You Up", "Rick Astley", 213000, "url1", "thumb1", "YouTube"),
        ExtractionResult("2", "Blinding Lights", "The Weeknd", 200000, "url2", "thumb2", "YouTube")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search music...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(modifier = Modifier.fillWeight(1f)) {
            items(mockResults) { result ->
                ListItem(
                    headlineContent = { Text(result.title) },
                    supportingContent = { Text(result.artist ?: "Unknown Artist") },
                    modifier = Modifier.clickable { onResultClick(result) }
                )
            }
        }
    }
}
