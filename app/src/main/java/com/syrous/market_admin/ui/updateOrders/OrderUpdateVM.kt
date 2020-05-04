package com.syrous.market_admin.ui.updateOrders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syrous.market_admin.data.Product
import com.syrous.market_admin.data.remote.RemoteApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderUpdateVM(private val remoteApi: RemoteApi) : ViewModel() {
    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> = _products

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(true)
                _products.postValue(remoteApi.getProducts())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.postValue(false)
            }
        }

    }

    fun updateProduct(index: Int, product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loading.postValue(true)

                //TODO :: insert update code here
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.postValue(false)
            }
        }
    }
}