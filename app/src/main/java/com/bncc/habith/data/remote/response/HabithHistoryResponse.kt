package com.bncc.habith.data.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HabithHistoryResponse(
    val id: String,
    val habithId: String,
    val date: String,
    val value: Int,
    val created_at: String,
    val updated_at: String
): Parcelable