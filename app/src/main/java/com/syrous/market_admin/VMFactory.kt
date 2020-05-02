package com.syrous.market_admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.syrous.market_admin.data.remote.RemoteApi

class VMFactory(private val remoteApi: RemoteApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass.isAssignableFrom(ActivityVM::class.java)) {
            ActivityVM(remoteApi) as T
        } else throw Exception("Unknown ViewModel")
}