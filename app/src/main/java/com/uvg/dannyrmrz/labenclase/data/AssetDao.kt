package com.uvg.dannyrmrz.labenclase.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface AssetDao {
    @Query("SELECT * FROM AssetEntity")
    suspend fun getAssets(): List<AssetEntity>

    @Upsert
    suspend fun insertAssets(monsters: List<AssetEntity>)

    @Query("SELECT * FROM AssetEntity WHERE id = :id")
    suspend fun getAssetById(id: kotlin.String): AssetEntity

    @Upsert
    suspend fun insertAsset(asset: AssetEntity) // Add this method
}
