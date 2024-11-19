package com.uvg.dannyrmrz.labenclase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.uvg.dannyrmrz.labenclase.data.AssetRepositoryImpl
import com.uvg.dannyrmrz.labenclase.data.KtorAssetsApi
import com.uvg.dannyrmrz.labenclase.data.KtorDependencies
import com.uvg.dannyrmrz.labenclase.domain.AssetRepository
import com.uvg.dannyrmrz.labenclase.domain.DataError
import com.uvg.dannyrmrz.labenclase.domain.onError
import com.uvg.dannyrmrz.labenclase.domain.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val assetRepo: AssetRepository
): ViewModel() {

    private val _state = MutableStateFlow(AssetListState())
    val state = _state.asStateFlow()

    init {
        getAssets()
    }

    fun getAssets() {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true,
                isGenericError = false,
                noInternetConnection = false
            )}

            assetRepo
                .getAllAssets()
                .onSuccess { assets ->
                    // Exitoso
                    _state.update { it.copy(
                        isLoading = false,
                        data = assets
                    ) }
                }
                .onError { error ->
                    if (error == DataError.NO_INTERNET) {
                        _state.update { it.copy(
                            isLoading = false,
                            noInternetConnection = true
                        ) }
                    } else {
                        _state.update { it.copy(
                            isLoading = false,
                            isGenericError = true,
                        ) }
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val context = checkNotNull(this[APPLICATION_KEY])
                val api = KtorAssetsApi(KtorDependencies.provideHttpClient())
                val db = KtorDependencies.provideLocalDb(
                    context = context
                )
                AssetListViewModel(
                    assetRepo = AssetRepositoryImpl(
                        assetApi = api,
                        assetDao = db.AssetDao()
                    )
                )
            }
        }
    }

}