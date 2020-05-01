package com.syrous.market_admin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.data.CustomerOrderConverter

@Database(
    entities = [CustomerOrderConverter::class],
    version = 1,
    exportSchema = true
)
abstract class OrderDatabase : RoomDatabase() {
    abstract fun orderDao(): CustomerOrderDao
}