package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order(
    val product_id: String,
    val product_name: String,
    val price: Int,
    val total_item_cost: String,
    val quantity: String,
    val unit: String
)