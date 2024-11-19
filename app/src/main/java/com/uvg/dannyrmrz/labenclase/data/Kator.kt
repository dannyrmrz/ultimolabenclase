package com.uvg.dannyrmrz.labenclase.data

import com.uvg.dannyrmrz.labenclase.domain.AssetsApi
import com.uvg.dannyrmrz.labenclase.domain.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import com.uvg.dannyrmrz.labenclase.domain.Result

class KtorAssetsApi(
    private val httpClient: HttpClient
): AssetsApi {
    override suspend fun getAllAssets(): Result<EntryListDto, NetworkError> {
        return safeCall<EntryListDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/"
            )
        }
    }

    override suspend fun getAssetProfile(id: Int): Result<EntryProfileDto, NetworkError> {
        return safeCall<EntryProfileDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/bitcoin/{id}"
            )
        }
    }
}