package com.spotifraud.app.ui.library

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spotifraud.app.domain.model.ExtractionResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    downloads: List<ExtractionResult>,
    onItemClick: (ExtractionResult) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Your Downloads",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(downloads) { download ->
                ListItem(
                    headlineContent = { Text(download.title) },
                    supportingContent = { Text(download.artist ?: "Unknown Artist") },
                    trailingContent = {
                        Text(
                            text = if (download.source == "Local") "Offline" else "Online",
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    modifier = Modifier.clickable { onItemClick(download) }
                )
            }
        }
    }
}
