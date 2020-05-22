package com.syrous.market_admin.ui.addProduct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.syrous.market_admin.AdminApplication
import com.syrous.market_admin.data.Product
import com.syrous.market_admin.databinding.ActivityAddProductBinding
import com.syrous.market_admin.ui.orders.MainActivity

class AddProductActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this,
        AddProductVMFactory((application as AdminApplication).appContainer.remoteApi))
            .get(AddProductVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonAdd.setOnClickListener {
                val productName = productNameEt.text.toString()
                val price = editPrice.text.toString()
                val quantity = editQuantity.text.toString()
                val unit = editUnit.text.toString()
                val stock = editStock.text.toString()
                val inStock = stockAvailableCheck.isChecked

                when {
                    productName.isEmpty() -> productNameEt.error = "Enter the Product Name"
                    price.isEmpty() -> editPrice.error = "Enter the Price"
                    quantity.isEmpty() -> editQuantity.error = "Enter the Quantity"
                    unit.isEmpty() -> editUnit.error = "Enter the Unit"
                    stock.isEmpty() -> editStock.error = "Enter the Stock"
                    else -> {
                        val product = Product(
                            "",
                            productName,
                            price.toInt(),
                            quantity.toInt(),
                            unit,
                            inStock,
                            stock.toInt()
                        )
                        viewModel.addProduct(product)
                        Toast.makeText(this@AddProductActivity, "Product Added!!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@AddProductActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }

        }

    }
}