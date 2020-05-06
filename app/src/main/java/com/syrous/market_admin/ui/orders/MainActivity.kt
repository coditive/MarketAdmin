package com.syrous.market_admin.ui.orders

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.syrous.lib_bluetooth.PosPrinter60mm
import com.syrous.market_admin.AdminApplication
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.databinding.ActivityMainBinding
import com.syrous.market_admin.ui.updateOrders.StockUpdateActivity
import com.syrous.market_admin.util.PrintUtil


class MainActivity : AppCompatActivity(), Toolbar.OnMenuItemClickListener {

    private var printer: PosPrinter60mm? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            VMFactory((application as AdminApplication).appContainer.remoteApi)
        ).get(ActivityVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        printer = PosPrinter60mm(this)
        printer?.setCharsetName("UTF-8")
        printer?.connect()

        val orderAdapter = OrderAdapter(OnclickListener())
        binding.apply {
            recyclerView.apply {
                adapter = orderAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            swipeToRefreshView.setOnRefreshListener { viewModel.reload() }
            viewModel.apply {
                orders.observe(this@MainActivity) { list ->
                    orderAdapter.submitList(list.sortedBy{it.timestamp}.reversed())
                }
                loading.observe(this@MainActivity) {
                    swipeToRefreshView.isRefreshing = it
                }
            }

            fabButton.setOnClickListener {
                Toast.makeText(this@MainActivity, "Main Activity", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, StockUpdateActivity::class.java))
            }

        }

    }

    inner class OnclickListener() {
        fun onClickPrint(customerOrder: CustomerOrder){
            Log.d("MainActivity", "onClick called, customer Order id : ${customerOrder.id}")
            printer?.let { PrintUtil(customerOrder, it).printReceipt() }
        }

        fun onCheckUpdatePayment(orderId: String, payment: String) {
            viewModel.hitPaymentDone(orderId, payment)
        }

        fun onCheckUpdateStatus(orderId: String){
            viewModel.hitOrderReady(orderId)
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val intent = Intent(this, StockUpdateActivity::class.java)
        startActivity(intent)
        return true
    }


}