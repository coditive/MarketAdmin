package com.syrous.market_admin.data.remote

import com.squareup.okhttp.RequestBody
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.data.PaymentDetails
import com.syrous.market_admin.data.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface RemoteApi {

    @GET("api/all_orders_details")
    suspend fun getAllOrdersFromRemote(): List<CustomerOrder>

    @PUT("api/payment_done/{order_id}")
    suspend fun paymentDoneCall(@Path("order_id") order_id: String, @Body paymentDetails: PaymentDetails)

    @PUT("api/order_ready/{order_id}")
    suspend fun orderReadyCall(@Path("order_id") order_id: String)

    //this will accept the changing parameters in form of products request body
    @PUT("api/update_products")
    suspend fun updateProduct(@Body products: Product)

    @GET("api/read-products")
    suspend fun getProducts(): List<Product>
}