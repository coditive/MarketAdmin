package com.syrous.market_admin.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.data.remote.RemoteApi
import java.io.IOException

class CustomerOrderRepo (
//    private val localDataSource: CustomerOrderDao,
    private val remoteDataSource: RemoteApi
) {

    private val _loadingState = MutableLiveData(false)
    val loadingState: LiveData<Boolean>
    get() = _loadingState

    private val _customerOrderFromRemote = MutableLiveData<List<CustomerOrder>>()
    val customerOrderFromRemote: LiveData<List<CustomerOrder>>
    get() = _customerOrderFromRemote

//    val customerOrderInMemory: LiveData<List<CustomerOrder>> = localDataSource.observeOrders()

    suspend fun loadCustomerOrders(){
        try {
            _loadingState.value = true
            val fetchOrders = remoteDataSource.getAllOrdersFromRemote()
            _customerOrderFromRemote.value = fetchOrders
            //TODO(): Update db cache

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            _loadingState.value = false
        }
    }

}