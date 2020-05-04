package com.syrous.market_admin.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    val id: String,
    val title: String,
    val price: Int,
    val quantity: Int,
    val unit: String,
    val inStock: Boolean,
    @Json(name = "stock") val stockQuantity: Int
)
