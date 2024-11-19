package com.uvg.dannyrmrz.labenclase.data

import kotlinx.serialization.Serializable

@Serializable
data class EntryProfileDto(
    val data: EntryDto,
    val message: String,
    val status: Int
)
