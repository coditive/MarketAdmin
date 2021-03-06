package com.syrous.market_admin.util

import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.syrous.market_admin.data.Order
import com.syrous.market_admin.data.OrderJsonAdapter
import kotlin.math.ceil

/**
 * This function is a ext fun, that takes
 * @param moshi
 * and converts in to json object which is used as body to network call
 */


fun Order.toJson(moshi: Moshi): String {
    val jsonAdapter = OrderJsonAdapter(moshi)
    return jsonAdapter.toJson(this)
}

//var sp: Float = px / getResources().getDisplayMetrics().scaledDensity
fun Float.toSp(resources: Resources): Int =
    ceil(this * resources.displayMetrics.scaledDensity).toInt()