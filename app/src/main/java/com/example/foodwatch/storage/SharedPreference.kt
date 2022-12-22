package com.example.foodwatch.storage

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "token"
        private const val KEY_NAME = "TOKEN"
    }
    fun saveToken(token: String) {
        val sharePref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharePref.edit().putString(KEY_NAME, token).apply()
    }
    fun readToken(): String{
        val sharePref: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharePref.getString(KEY_NAME, null) ?: ""
    }
}