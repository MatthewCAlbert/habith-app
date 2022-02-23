package com.bncc.habith.data.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabithResponse2(
    val success: Boolean,
    val message: String,
    val data: Data
): Parcelable {
    @Parcelize
    data class Data(
        val id: String? = null,
        val userId: String? = null,
        val title: String,
        val category: String,
        val description: String,
        val target: Int,
        val target_type: String? = "none",
        val start: String? = null,
        val end: String? = null,
        val repeat_every_day: String,
        val created_at: String? = null,
        val updated_at: String? = null
    ): Parcelable
}