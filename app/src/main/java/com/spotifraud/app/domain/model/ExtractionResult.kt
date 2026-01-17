package com.spotifraud.app.domain.model

data class ExtractionResult(
    val id: String,
    val title: String,
    val artist: String?,
    val durationMs: Long,
    val streamUrl: String?,
    val thumbnailUrl: String?,
    val source: String
)
