package com.syrous.market_admin.ui.addProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syrous.market_admin.data.remote.RemoteApi

@Suppress("UNCHECKED_CAST")
class AddProductVMFactory(private val remoteApi: RemoteApi): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(AddProductVM::class.java)) {
            AddProductVM(remoteApi) as T
        } else throw Exception("Unknown ViewModel")
}