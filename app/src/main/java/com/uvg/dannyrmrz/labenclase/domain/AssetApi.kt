package com.uvg.dannyrmrz.labenclase.domain

import com.uvg.dannyrmrz.labenclase.data.EntryListDto
import com.uvg.dannyrmrz.labenclase.data.EntryProfileDto
import com.uvg.dannyrmrz.labenclase.domain.Result

interface AssetsApi {
    suspend fun getAllAssets(): Result<EntryListDto, NetworkError>
    suspend fun getAssetProfile(id: Int): Result<EntryProfileDto, NetworkError>
}