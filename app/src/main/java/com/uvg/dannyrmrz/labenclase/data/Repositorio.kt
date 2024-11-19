package com.uvg.dannyrmrz.labenclase.data

import com.uvg.dannyrmrz.labenclase.domain.AssetRepository
import com.uvg.dannyrmrz.labenclase.domain.Assets
import com.uvg.dannyrmrz.labenclase.domain.AssetsApi
import com.uvg.dannyrmrz.labenclase.domain.DataError
import com.uvg.dannyrmrz.labenclase.domain.NetworkError
import com.uvg.dannyrmrz.labenclase.data.mapToAssetEntity
import com.uvg.dannyrmrz.labenclase.data.mapToAssetModel
import com.uvg.dannyrmrz.labenclase.domain.Result

class AssetRepositoryImpl(
    private val assetApi: AssetsApi,
    private val assetDao: AssetDao
): AssetRepository {
    override suspend fun getAllAssets(): Result<List<Assets>, DataError> {

        when (val result = assetApi.getAllAssets()) {
            is Result.Error -> {
                println(result.error)

                val localMonsters = assetDao.getAssets()
                if (localMonsters.isEmpty()) {
                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }

                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localMonsters.map { it.mapToAssetModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteMonsters = result.data.data

                assetDao.insertAssets(
                    remoteMonsters.map { it.mapToAssetEntity() }
                )
                return Result.Success(
                    remoteMonsters.map { it.mapToAssetModel() }
                )
            }
        }
    }

    override suspend fun getOneAsset(id: String): Result<Assets, DataError> {

        val localMonster = assetDao.getAssetById(id)
        return Result.Success(
            localMonster.mapToAssetModel()
        )
    }

    override suspend fun saveAssetOffline(asset: Assets) {
        assetDao.insertAsset(asset.mapToAssetEntity())
    }
}