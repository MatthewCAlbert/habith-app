package com.bncc.habith.data.remote.response

data class HabithHistoryResponse(
    val id: String,
    val habithId: String,
    val date: String,
    val value: Int,
    val created_at: String,
    val updated_at: String
)