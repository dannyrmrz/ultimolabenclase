package com.uvg.dannyrmrz.labenclase.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import com.uvg.dannyrmrz.labenclase.data.AssetRepositoryImpl
import com.uvg.dannyrmrz.labenclase.data.KtorAssetsApi
import com.uvg.dannyrmrz.labenclase.data.KtorDependencies
import com.uvg.dannyrmrz.labenclase.domain.AssetRepository
import com.uvg.dannyrmrz.labenclase.domain.onError
import com.uvg.dannyrmrz.labenclase.domain.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AssetProfileViewModel(
    private val assetRepository: AssetRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val assetProfile = savedStateHandle.toRoute<AssetProfileDestination>()

    private val _state = MutableStateFlow(CryptoProfileState())
    val state = _state.asStateFlow()

    init {
        getAssetById()
    }

    private fun getAssetById() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
            ) }
            delay(1000L)
            assetRepository
                .getOneAsset(id = assetProfile.id)
                .onSuccess { asset ->
                    _state.update { it.copy(
                        isLoading = false,
                        asset = asset,
                        isGenericError = false
                    ) }
                }
                .onError {
                    _state.update { it.copy(
                        isLoading = false,
                        isGenericError = true
                    ) }
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveOffline() {
        viewModelScope.launch {
            val currentAsset = state.value.asset ?: return@launch
            assetRepository.saveAssetOffline(currentAsset)
            _state.update { it.copy(
                isOffline = true,
                lastUpdate = getCurrentDate()
            ) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.now().format(formatter)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorAssetsApi(KtorDependencies.provideHttpClient())
                val db = KtorDependencies.provideLocalDb(
                    context = context
                )
                AssetProfileViewModel(
                    assetRepository = AssetRepositoryImpl(
                        assetApi = api,
                        assetDao = db.AssetDao()
                    ),
                    savedStateHandle = this.createSavedStateHandle()
                )
            }
        }
    }
}