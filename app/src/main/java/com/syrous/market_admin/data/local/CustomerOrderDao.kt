package com.syrous.market_admin.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.syrous.market_admin.data.CustomerOrder

@Dao
interface CustomerOrderDao {

    @Query("SELECT * FROM customer_orders")
    fun observeOrders(): LiveData<List<CustomerOrder>>

    @Query("UPDATE `customer_orders` SET delivery_status = :status WHERE id = :id")
    suspend fun setPaymentDone(status: String = "paid", id: String)

    @Query("UPDATE `customer_orders` SET payment_status = :status WHERE id = :id")
    suspend fun setOrderReady(status: String = "ready", id: String)

    @Insert
    suspend fun insertOrder(order: CustomerOrder)
}