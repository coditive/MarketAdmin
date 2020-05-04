package com.syrous.market_admin.ui.updateOrders


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.syrous.market_admin.data.Product
import com.syrous.market_admin.databinding.ProductStockItemBinding


class UpdateOrderAdapter(private val clickHandler: StockUpdateActivity.StockUpdateClickListener) :
    androidx.recyclerview.widget.ListAdapter<Product, UpdateOrderAdapter.CompactViewHolder>(
        CALLBACK
    ) {
    inner class CompactViewHolder(private val binding: ProductStockItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                productName.text = product.title
                editQuantity.setText(product.quantity.toString())
                editStock.setText(product.stockQuantity.toString())
                stockAvailableCheck.isChecked = product.inStock
                editPrice.setText(String.format("%d", product.price))
                buttonUpdate.setOnClickListener {
                    clickHandler.onUpdate(
                        adapterPosition, product.copy(
                            inStock = stockAvailableCheck.isChecked,
                            price = Integer.parseInt(editPrice.text.toString()),
                            quantity = Integer.parseInt(editQuantity.text.toString()),
                            stockQuantity = Integer.parseInt(editStock.text.toString())
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CompactViewHolder(
        ProductStockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun submitList(list: List<Product>?) {
        super.submitList(
            if (list.isNullOrEmpty()) {
                listOf()
            } else {
                list.toList()
            }
        )
    }

    override fun onBindViewHolder(holder: CompactViewHolder, position: Int) =
        holder.bind(getItem(position))

    private object CALLBACK : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem.inStock == newItem.inStock &&
                    oldItem.price == newItem.price &&
                    oldItem.quantity == newItem.quantity &&
                    oldItem.stockQuantity == newItem.stockQuantity
    }
}
