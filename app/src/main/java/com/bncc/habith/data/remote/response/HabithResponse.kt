package com.bncc.habith.data.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabithResponse(
    val id: String,
    val userId: String,
    val title: String,
    val category: String,
    val description: String,
    val target: Int,
    val target_type: String,
    val start: String?,
    val end: String?,
    val repeat_every_day: String,
    val repeat_on: String?,
    val history: List<HabithHistoryResponse>?,
    val created_at: String,
    val updated_at: String
): Parcelable