package com.syrous.market_admin

import android.app.Application

class AdminApplication: Application(){
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}