package com.syrous.market_admin.data

import com.squareup.moshi.JsonClass

/**
 * This data class will content values required for making order.
 * @property order will specify all the ordered product which quantity, id and total_cost for that quantity.
 * @property total_cost will specify the total_cost of all products combined together.
 */

@JsonClass(generateAdapter = true)
data class CustomerOrder(
    val id: String,
    val user_id: String,
    val first_name: String,
    val last_name: String,
    val phone: Int,
    val address: Address,
    val order: List<Order>,
    val total_cost: String,
    val delivery_status: String,
    val transaction_id: String,
    val timestamp: Long //timestamp should just be epoch time
)
