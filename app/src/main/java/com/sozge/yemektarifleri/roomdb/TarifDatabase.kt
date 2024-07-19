package com.sozge.yemektarifleri.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sozge.yemektarifleri.model.Tarif

@Database(entities = [Tarif::class], version = 1)
abstract class TarifDatabase : RoomDatabase() {
    abstract fun tarifDao() : TarifDAO
}