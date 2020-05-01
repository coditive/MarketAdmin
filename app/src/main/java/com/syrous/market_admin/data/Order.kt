package com.syrous.market_admin.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(
    val product_id: String,
    val product_name: String,
    val price: Int,
    val total_item_cost: Int,
    val quantity: Double
)