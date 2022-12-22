package com.example.foodwatch.order

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodwatch.databinding.ItemOrderBinding
import com.example.foodwatch.model.Dishes
import com.squareup.picasso.Picasso

class OrderAdapter(private val context: Context) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private val foods: MutableList<Dishes> = mutableListOf()

    class OrderViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Dishes, context: Context) {

            Log.d("MyLog", item.toString())

            Picasso.with(context)
                .load(item.dish.icon)
                .into(binding.image)

            with(binding) {
                name.text = item.dish.name
                pieces.text = item.count
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        return OrderViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) =
        holder.bind(foods[position], context)

    override fun getItemCount() = foods.size

    fun addItem(item: Dishes) {
        foods.add(item)
        notifyItemInserted(foods.lastIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<Dishes>) {
        foods.clear()
        foods.addAll(items)
        Log.d("MyLog", foods.toString())
        notifyDataSetChanged()
    }
}