package com.spotifraud.app.domain.repository

import com.spotifraud.app.domain.model.ExtractionResult
import kotlinx.coroutines.flow.Flow

interface ExtractionRepository {
    suspend fun extractMetadata(url: String): ExtractionResult
    fun search(query: String): Flow<List<ExtractionResult>>
}
