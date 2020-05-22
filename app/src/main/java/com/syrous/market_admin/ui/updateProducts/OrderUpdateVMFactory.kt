package com.syrous.market_admin.ui.updateProducts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syrous.market_admin.data.remote.RemoteApi

class OrderUpdateVMFactory(private val remoteApi: RemoteApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(OrderUpdateVM::class.java)) {
            OrderUpdateVM(remoteApi) as T
        } else throw Exception("Unknown ViewModel")
}