package com.syrous.market_admin.ui.addProduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syrous.market_admin.data.Product
import com.syrous.market_admin.data.remote.RemoteApi
import kotlinx.coroutines.launch

class AddProductVM(val remoteApi: RemoteApi) : ViewModel() {
    fun addProduct(product: Product) {
        viewModelScope.launch {
            remoteApi.addProduct(product)
        }
    }
}