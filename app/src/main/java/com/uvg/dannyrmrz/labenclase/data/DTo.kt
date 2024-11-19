package com.uvg.dannyrmrz.labenclase.data

import com.uvg.dannyrmrz.labenclase.domain.Assets
import kotlinx.serialization.Serializable

@Serializable
data class EntryDto(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?
)


fun EntryDto.mapToAssetModel(): Assets {
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


fun EntryDto.mapToAssetEntity(): AssetEntity {
    return AssetEntity(
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
