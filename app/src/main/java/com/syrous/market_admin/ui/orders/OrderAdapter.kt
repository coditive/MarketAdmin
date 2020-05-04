package com.syrous.market_admin.ui.orders

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syrous.market_admin.data.CustomerOrder
import com.syrous.market_admin.databinding.OrderItemBinding
import com.syrous.market_admin.util.Truss
import com.syrous.market_admin.util.toSp
import java.util.*

class OrderAdapter(private val clickHandler: MainActivity.OnclickListener) : ListAdapter<CustomerOrder, OrderAdapter.OrderViewHolder>(
    CALLBACK
) {
    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(customerOrder: CustomerOrder) {
            binding.apply {
                textViewName.text =
                    String.format("%s %s", customerOrder.first_name, customerOrder.last_name)
                timeStamp.text = java.text.SimpleDateFormat("dd-MMM-yyyy HH:mm a")
                    .format(Date(customerOrder.timestamp))
                textViewContact.text = customerOrder.phone.toString()
                val truss = Truss()
                customerOrder.order.forEach {
                    truss
                        .pushSpan(StyleSpan(Typeface.BOLD))
                        .pushSpan(AbsoluteSizeSpan(18f.toSp(textViewContact.resources)))
                        .append(it.product_name)
                        .popSpan()
                        .popSpan()
                        .append(" x ")
                        .pushSpan(StyleSpan(Typeface.BOLD))
                        .pushSpan(AbsoluteSizeSpan(18f.toSp(textViewContact.resources)))
                        .append("${it.quantity} ${it.unit}")
                        .popSpan()
                        .popSpan()
                        .append(" = ")
                        .pushSpan(AbsoluteSizeSpan(24f.toSp(textViewContact.resources)))
                        .pushSpan(StyleSpan(Typeface.BOLD))
                        .append(" ₹ ${it.total_item_cost}\n")
                        .popSpan()
                        .popSpan()
                }
                textViewOrders.text = truss.build()
                textViewTotal.text = Truss()
                    .append("Total amount = ")
                    .pushSpan(StyleSpan(Typeface.BOLD))
                    .pushSpan(AbsoluteSizeSpan(24f.toSp(textViewContact.resources)))
                    .append("₹ ${customerOrder.total_cost}").build()

                buttonPrint.setOnClickListener{
                    clickHandler.onClickPrint(customerOrder)
                    it.isClickable = false
                }

                checkBoxPaid.setOnCheckedChangeListener { _, _ ->
                    clickHandler.onCheckUpdatePayment(customerOrder.id, customerOrder.total_cost)}

                checkBoxReady.setOnCheckedChangeListener{ _, _ ->
                    clickHandler.onCheckUpdateStatus(customerOrder.id) }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder =
        OrderViewHolder(
            OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object CALLBACK : DiffUtil.ItemCallback<CustomerOrder>() {
        override fun areItemsTheSame(oldItem: CustomerOrder, newItem: CustomerOrder) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CustomerOrder, newItem: CustomerOrder) = true
    }
}