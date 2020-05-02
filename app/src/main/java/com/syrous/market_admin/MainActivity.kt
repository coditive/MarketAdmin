package com.syrous.market_admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uiScope = CoroutineScope(Dispatchers.IO)

        val remoteApi =  (this.application as AdminApplication).appContainer.remoteApi

        uiScope.launch {
           remoteApi.getAllOrdersFromRemote()
        }



    }
}
