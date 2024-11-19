package com.uvg.dannyrmrz.labenclase.data

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AssetEntity::class],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun AssetDao(): AssetDao
}