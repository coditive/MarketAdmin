package com.syrous.market_admin.ui.updateProducts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.syrous.market_admin.AdminApplication
import com.syrous.market_admin.data.Product
import com.syrous.market_admin.databinding.ActivityStockUpdateBinding

class StockUpdateActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            OrderUpdateVMFactory((application as AdminApplication).appContainer.remoteApi)
        ).get(OrderUpdateVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStockUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val orderAdapter = UpdateOrderAdapter(StockUpdateClickListener())
        binding.apply {
            recyclerView.apply {
                adapter = orderAdapter
                layoutManager = LinearLayoutManager(this@StockUpdateActivity)

            }
            swipeToRefreshView.setOnRefreshListener {
                viewModel.reload()
            }
        }

        viewModel.apply {
            products.observe(this@StockUpdateActivity) {
                orderAdapter.submitList(it)
            }
            loading.observe(this@StockUpdateActivity) {
                binding.swipeToRefreshView.isRefreshing = it
            }
        }

    }

   inner class StockUpdateClickListener {
        fun onUpdate(index: Int, product: Product) {
         viewModel.updateProduct(index, product)
            Toast.makeText(this@StockUpdateActivity, "Order Updated Successfully !",Toast.LENGTH_SHORT).show()
        }
    }
}
