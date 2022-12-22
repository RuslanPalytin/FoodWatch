package com.example.foodwatch.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.foodwatch.api.ApiService
import com.example.foodwatch.databinding.ActivitySignInBinding
import com.example.foodwatch.model.TokenModel
import com.example.foodwatch.model.UserLoginModel
import com.example.foodwatch.storage.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : Activity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSignIn.setOnClickListener {

            if (Patterns.EMAIL_ADDRESS.matcher(binding.textInputEmail.text)
                    .matches() && binding.textInputPassword.text.length > 8
            ) {

                val loginModel = UserLoginModel(
                    email = binding.textInputEmail.text.toString(),
                    password = binding.textInputPassword.text.toString()
                )
                val token = SharedPreference(this)

                ApiService.retrofit.loginUser(loginModel).enqueue(object : Callback<TokenModel> {
                    override fun onResponse(
                        call: Call<TokenModel>,
                        response: Response<TokenModel>
                    ) {
                        if(response.isSuccessful){
                            token.saveToken(response.body()?.token!!)
                            startActivity(Intent(applicationContext, OrderActivity::class.java))
                            Toast.makeText(applicationContext, "Success!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Введены неверные значения", Toast.LENGTH_SHORT).show()
            }
        }
    }
}