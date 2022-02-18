package com.bncc.habith.util

import android.content.SharedPreferences
import javax.inject.Inject

class UserPref @Inject constructor(
    private val shared: SharedPreferences
) {

    private val keyIsLogin = "isLogin"
    private val keyToken = "token"

    fun isLogin() = shared.getBoolean(keyIsLogin, false)

    fun getToken() = shared.getString(keyToken, "")

    fun refreshToken(new: String) {
        val editor = shared.edit()
        editor.putString(keyToken, "Bearer $new")
        editor.apply()
    }

    fun setToken(token: String) {
        val editor = shared.edit()
        editor.putString(keyToken, "Bearer $token")
        editor.putBoolean(keyIsLogin, true)
        editor.apply()
    }

    fun clearSession() {
        val editor = shared.edit()
        editor.remove(keyIsLogin)
        editor.remove(keyToken)
        editor.apply()
    }
}