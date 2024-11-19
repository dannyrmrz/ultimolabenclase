package com.uvg.dannyrmrz.labenclase.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uvg.dannyrmrz.labenclase.domain.Assets


@Entity
data class AssetEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?
)


fun AssetEntity.mapToAssetModel(): Assets {
    return Assets(
        id = id,
        name = name,
        symbol = symbol,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr,
        supply = supply,
        maxSupply = maxSupply,
        marketCapUsd = marketCapUsd
    )
}