package com.bncc.habith.data.remote.network.interceptor

import com.bncc.habith.util.UserPref
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Custom interceptor to add Authorization bearer token on every api request
 */
class TokenInterceptor @Inject constructor(
    private val pref: UserPref
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = "${pref.getToken()}"
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(newRequest)
    }
}