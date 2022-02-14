package com.bncc.habith.data.remote.response

data class HabithResponse(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val target: Int? = null,
    val target_type: TargetType? = TargetType.NONE
){
    enum class TargetType {
        ITE, GTE, EQ, NONE
    }
}