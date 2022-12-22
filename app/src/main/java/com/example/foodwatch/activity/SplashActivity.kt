package com.example.foodwatch.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.foodwatch.databinding.ActivitySplashBinding
import com.example.foodwatch.storage.SharedPreference

@SuppressLint("CustomSplashScreen")
class SplashActivity : Activity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = SharedPreference(this)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            if(token.readToken() == "") {
                startActivity(Intent(this, SignInActivity::class.java))
            } else {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }, 3000)
    }
}