package com.example.foodwatch.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.foodwatch.api.ApiService
import com.example.foodwatch.databinding.ActivityOrderBinding
import com.example.foodwatch.model.OrderModel
import com.example.foodwatch.order.OrderAdapter
import com.example.foodwatch.storage.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : Activity() {

    private lateinit var binding: ActivityOrderBinding
    private lateinit var adapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val token = SharedPreference(this)

        adapter = OrderAdapter(this)
        binding.recyclerView.adapter = adapter

        ApiService.retrofit.getHistoryOrder("Bearer ${token.readToken()}").enqueue(object : Callback<List<OrderModel>>{
            override fun onResponse(
                call: Call<List<OrderModel>>,
                response: Response<List<OrderModel>>
            ) {
                if(response.isSuccessful){
                    adapter.submitList(response.body()!![5].dishes)
                    binding.address.text = response.body()!![5].address
                    binding.date.text = response.body()!![5].date
                } else {
                    Toast.makeText(applicationContext, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<OrderModel>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        binding.imageNavigation.setOnClickListener {
            startActivity(Intent(this, MapActivity::class.java))
        }
    }
}