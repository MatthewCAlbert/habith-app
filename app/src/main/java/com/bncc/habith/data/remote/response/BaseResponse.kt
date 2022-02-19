package com.bncc.habith.data.remote.response

data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)
