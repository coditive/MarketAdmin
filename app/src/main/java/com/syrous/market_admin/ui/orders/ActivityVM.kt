package com.syrous.market_admin.ui.orders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.data.PaymentDetails
import com.syrous.market_admin.data.remote.RemoteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityVM(private val remoteApi: RemoteApi) : ViewModel() {
    private val _orders: MutableLiveData<List<CustomerOrder>> = MutableLiveData()
    val orders: LiveData<List<CustomerOrder>> = _orders

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(true)
                _orders.postValue(remoteApi.getAllOrdersFromRemote())
            } catch (e: Exception) {
                Log.e("ActivityVM", "unable to fetch orders ", e)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun hitPaymentDone(orderId: String, payment: String) {
        viewModelScope.launch(Dispatchers.IO){
            remoteApi.paymentDoneCall(orderId, PaymentDetails(received_payment = payment))
        }
    }

    fun hitOrderReady(orderId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteApi.orderReadyCall(orderId)
        }
    }
}