package com.uvg.dannyrmrz.labenclase.presentation

import com.uvg.dannyrmrz.labenclase.domain.Assets

data class AssetListState(
    val isLoading: Boolean = true,
    val data: List<Assets> = emptyList(),
    val isGenericError: Boolean = false,
    val noInternetConnection: Boolean = false
)