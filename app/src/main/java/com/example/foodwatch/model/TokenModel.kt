package com.example.foodwatch.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("access_token")
    val token: String
)
