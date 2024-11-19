package com.uvg.dannyrmrz.labenclase.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun CryptoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CryptoListDestination,
        modifier = modifier
    ) {
        assetList(navigateToAssetProfile = { navController.navigateToAssetProfile(it) })
        assetProfile(onNavigateBack = navController::navigateUp)
    }
}