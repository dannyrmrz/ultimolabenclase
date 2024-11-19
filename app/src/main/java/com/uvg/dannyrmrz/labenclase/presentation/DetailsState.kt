package com.uvg.dannyrmrz.labenclase.presentation

import com.uvg.dannyrmrz.labenclase.domain.Assets

data class CryptoProfileState(
    val isLoading: Boolean = true,
    val asset: Assets? = null,
    val isGenericError: Boolean = false,
    val isOffline: Boolean = false,
    val lastUpdate: String = ""
)
