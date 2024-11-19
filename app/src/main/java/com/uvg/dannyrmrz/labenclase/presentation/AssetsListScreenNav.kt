package com.uvg.dannyrmrz.labenclase.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
data object CryptoListDestination

fun NavGraphBuilder.assetList(
    navigateToAssetProfile: (String) -> Unit
) {
    composable<CryptoListDestination> {
        CryptoListRoute(
            onCryptoClick = navigateToAssetProfile
        )
    }
}