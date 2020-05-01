package com.syrous.market_admin.data.remote

import com.syrous.market_admin.data.CustomerOrder
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RemoteApi {

    @GET("api/all_orders_details")
    suspend fun getAllOrdersFromRemote(): List<CustomerOrder>

    @PUT("api/payment_done/{order_id}")
    suspend fun paymentDoneCall(@Path("order_id") order_id: String)

    @PUT("api/order_ready/{order_id}")
    suspend fun orderReadyCall(@Path("order_id") order_id: String)
}