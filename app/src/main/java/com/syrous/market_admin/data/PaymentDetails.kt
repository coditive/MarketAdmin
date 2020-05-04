package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PaymentDetails (
    val payment_status: String = "in_cash",
    val received_by: String = "admin",
    val received_payment: String
)