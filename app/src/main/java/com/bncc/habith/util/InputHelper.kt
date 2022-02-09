package com.bncc.habith.util

import android.content.Context
import com.bncc.habith.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
}