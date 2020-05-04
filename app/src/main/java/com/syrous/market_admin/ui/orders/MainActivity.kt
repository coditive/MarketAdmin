package com.syrous.market_admin.ui.orders

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.syrous.lib_bluetooth.DeviceCallbacks
import com.syrous.lib_bluetooth.PosPrinter60mm
import com.syrous.market_admin.AdminApplication
import com.syrous.market_admin.databinding.ActivityMainBinding
import com.syrous.market_admin.ui.updateOrders.StockUpdateActivity
import com.syrous.market_admin.util.PrintUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val printer by lazy { PosPrinter60mm(this) }

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
        printer.setCharsetName("UTF-8")
        printer.setDeviceCallbacks(object : DeviceCallbacks {
            override fun onFailure() {
                Toast.makeText(this@MainActivity, "Device Connection failed!!", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onConnected() {
                Toast.makeText(this@MainActivity, "Device Connected!!", Toast.LENGTH_SHORT).show()
            }

            override fun onDisconnected() {
                Toast.makeText(this@MainActivity, "Device disconnected!!", Toast.LENGTH_SHORT)
                    .show()
            }

        })

        printer.connect()
        val orderAdapter = OrderAdapter()
        viewModel.apply {
            orders.observe(this@MainActivity) {
                orderAdapter.submitList(it)
            }
            loading.observe(this@MainActivity) {
                swipeToRefreshView.isRefreshing = it
            }
        }
        binding.apply {
            recyclerView.apply {
                adapter = orderAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            swipeToRefreshView.setOnRefreshListener { viewModel.reload() }
            toolbar.apply {

            }
        }
        viewModel.orders.observe(this@MainActivity) {
            it.forEach { customerOrder ->
                PrintUtil(customerOrder, printer).printReceipt()
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        startActivity(Intent(this, StockUpdateActivity::class.java))
        return true
    }
}
