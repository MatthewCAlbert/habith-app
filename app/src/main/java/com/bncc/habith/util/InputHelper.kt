package com.bncc.habith.util

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.bncc.habith.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.format.DateTimeFormatter
import java.util.*

object InputHelper {

    fun inputIsEmpty(
        inputEditText: TextInputEditText,
        inputLayout: TextInputLayout,
        context: Context
    ): Boolean {
        if (inputEditText.text.toString().isEmpty()) {
            inputLayout.error = context.getString(R.string.input_is_empty_message)
            inputEditText.requestFocus()
            return true
        }

        return false
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fixedDate(date: String): String {
        if (date != "null"){
            val default = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
                Locale.getDefault()
            )
            val fixed = DateTimeFormatter.ofPattern("dd MMM, yyy", Locale.getDefault())
            return fixed.format(default.parse(date))
        }
        return "-"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fixedDate2(date: String): String {
        if (date != "null"){
            val default = DateTimeFormatter.ofPattern(
                "dd/MM/yy HH:mm",
                Locale.getDefault()
            )
            val fixed = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm", Locale.getDefault())
            return fixed.format(default.parse(date))
        }
        return "-"
    }

}