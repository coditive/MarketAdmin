package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Address(
    val address_loc: String,
    val address_type: String,
    val address_pin: Int
)