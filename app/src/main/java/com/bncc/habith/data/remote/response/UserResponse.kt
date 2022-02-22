package com.bncc.habith.data.remote.response

data class UserResponse(
    val success: Boolean,
    val message: String,
    val data: Data,
) {
    data class Data(
        val user: User,
        val token: String?,
        val expiresIn: String?
    ) {
        data class User(
            val id: String,
            val username: String,
            val email: String,
            val name: String,
            val created_at: String,
            val updated_at: String,
            val hash: String?,
            val salt: String?
        )
    }
}
