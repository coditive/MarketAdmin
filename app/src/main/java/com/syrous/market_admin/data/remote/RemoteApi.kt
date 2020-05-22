package com.syrous.market_admin.data.remote

import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.data.PaymentDetails
import com.syrous.market_admin.data.Product
import retrofit2.http.*

interface RemoteApi {

    @GET("api/all_orders_details")
    suspend fun getAllOrdersFromRemote(): List<CustomerOrder>

    @PUT("api/payment_done/{order_id}")
    suspend fun paymentDoneCall(@Path("order_id") order_id: String, @Body paymentDetails: PaymentDetails)

    @PUT("api/order_ready/{order_id}")
    suspend fun orderReadyCall(@Path("order_id") order_id: String)

    //this will accept the changing parameters in form of products request body
    @PUT("api/update_product/{product_id}")
    suspend fun updateProduct(@Path("product_id")product_id: String,@Body products: Product)

    @GET("api/read-products")
    suspend fun getProducts(): List<Product>

    @POST("api/add_product")
    suspend fun addProduct(@Body products: Product)
}