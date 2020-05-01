package com.syrous.market_admin.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "customer_orders")
data class CustomerOrderConverter (
    @PrimaryKey val id: String,
    val order: String,
    val total_cost: Int,
    val delivery_status: String,
    val mode_of_payment: String,
    val payment_status: String
)