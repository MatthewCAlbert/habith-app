package com.bncc.habith.util.extension

import android.annotation.SuppressLint
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun createHandleDatePicker(
    title: String,
    onGetResult: (String) -> Unit
): MaterialDatePicker<Long> {
    return MaterialDatePicker.Builder.datePicker()
        .setTitleText(title)
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build().apply {
            addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("dd/MM/yy")
                val date = dateFormatter.format(Date(it))
                onGetResult(date)
            }
        }
}

fun createTimePicker(
    title: String,
    onGetResult: (String) -> Unit,
    onCancel: () -> Unit
): MaterialTimePicker {
    return MaterialTimePicker.Builder()
        .setTimeFormat(TimeFormat.CLOCK_24H)
        .setTitleText(title)
        .build().apply {
            addOnPositiveButtonClickListener {
                val hour = this.hour
                val minute = this.minute
                val formattedTime =
                    if(minute < 10)
                        "${hour}:0${minute}"
                    else
                        "${hour}:${minute}"
                onGetResult(formattedTime)
            }
            addOnNegativeButtonClickListener {
                onCancel()
            }
        }
}