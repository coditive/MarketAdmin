package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Transaction(
    val mode_of_payment: String,
    val payment_status: String,
    val received_by: String,
    val received_payment: Double,
    val timestamp: Long,
    val total_cost: Double,
    val user_id: String
)