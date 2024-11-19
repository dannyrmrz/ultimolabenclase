package com.uvg.dannyrmrz.labenclase.data

import kotlinx.serialization.Serializable

@Serializable
data class EntryListDto(
    val data: List<EntryDto>,
    val message: String = "",
    val status: Int = 0
)
