package com.syrous.market_admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.syrous.market_admin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        val orderAdapter = OrderAdapter()
        viewModel.apply {
            orders.observe(this@MainActivity) {
                orderAdapter.submitList(it)
            }
            loading.observe(this@MainActivity) {
                swipeToRefreshView.isRefreshing = it
            }
        }
        binding.recyclerView.apply {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)

            swipeToRefreshView.setOnRefreshListener { viewModel.reload() }
        }
    }


}
