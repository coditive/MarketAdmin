package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Timestamp(
    val _seconds: Long,
    val _nanoseconds: Long
)