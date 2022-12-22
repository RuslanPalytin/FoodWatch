package com.example.foodwatch.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.foodwatch.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this, SplashActivity::class.java))
    }
}