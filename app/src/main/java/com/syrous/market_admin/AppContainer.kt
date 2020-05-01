package com.syrous.market_admin

import android.content.Context
import androidx.room.Room
import com.syrous.market_admin.data.local.OrderDatabase
import com.syrous.market_admin.data.remote.RemoteApi
import com.syrous.market_admin.utli.BASE_URL
import com.syrous.market_admin.utli.ORDER_DATABASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer(application: AdminApplication) {
    val remoteApi: RemoteApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                if (BuildConfig.DEBUG) {
                    val httpLoggingInterceptor = HttpLoggingInterceptor()
                    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    this.addInterceptor(httpLoggingInterceptor)
                }
            }.build())
            .build()
            .create(RemoteApi::class.java)

    private val db =
        Room.databaseBuilder(
            application,
            OrderDatabase::class.java,
            ORDER_DATABASE
        ).build()

 }
