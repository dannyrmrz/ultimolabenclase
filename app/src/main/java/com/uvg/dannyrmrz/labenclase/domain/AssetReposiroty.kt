package com.uvg.dannyrmrz.labenclase.domain



interface AssetRepository {
    suspend fun getAllAssets(): Result<List<Assets>, DataError>
    suspend fun getOneAsset(id: String): Result<Assets, DataError>
    suspend fun saveAssetOffline(asset: Assets)
}