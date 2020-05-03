package com.syrous.market_admin.utli

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Printer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.syrous.lib_bluetooth.DeviceCallbacks
import com.syrous.lib_bluetooth.PosPrinter
import com.syrous.lib_bluetooth.PosPrinter60mm
import com.syrous.lib_bluetooth.TicketBuilder
import com.syrous.market_admin.R
import com.syrous.market_admin.data.CustomerOrder
import kotlinx.coroutines.internal.artificialFrame
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class PrintUtil (private val customerOrder: CustomerOrder, private val printer: PosPrinter){

    @SuppressLint("SimpleDateFormat")
    fun printReceipt(){
        val ticket = TicketBuilder(printer)
            .isCyrillic(true)
            .header("Receipt")
            .divider()
            .text("Order Id : ${customerOrder.id}")
            .text("Customer Name : ${customerOrder.first_name +" "+ customerOrder.last_name}")
            .text("Date : ${SimpleDateFormat("dd.MM.yyyy").format(Date(customerOrder.timestamp))}")
            .text(("Time : ${SimpleDateFormat("HH:mm").format(Date(customerOrder.timestamp))}"))
            .divider()
            .subHeader("Order").build()

        printer.send(ticket)
       val ticketOrderBuilder = TicketBuilder(printer)
        customerOrder.order.forEach {
            ticketOrderBuilder.menuLine("- ${it.quantity} ${it.product_name}", it.total_item_cost)
        }
        ticketOrderBuilder
            .dividerDouble()
            .menuLine("Total Cost : ", customerOrder.total_cost)
            .dividerDouble()
            .stared("THANK YOU")
            .feedLine(4)
            .build()

        printer.send(ticketOrderBuilder.build())
    }


}