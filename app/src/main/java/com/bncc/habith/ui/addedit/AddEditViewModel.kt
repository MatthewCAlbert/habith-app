package com.bncc.habith.ui.addedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddEditViewModel : ViewModel() {

    private val showDatePicker: MutableLiveData<Boolean> = MutableLiveData()
    private val showTimePicker: MutableLiveData<Boolean> = MutableLiveData()

    private val setStartDateTime: MutableLiveData<String> = MutableLiveData()
    private val setEndDateTime: MutableLiveData<String> = MutableLiveData()

    private var dateTimeStr: String = ""
    private var timeStr: String = ""

    fun onClickDateTimeEditText(isTypeStart: Boolean) {
        clearDateTime()
        showDatePicker.value = isTypeStart
    }

    fun onDateSelected(newValue: String, isTypeStart: Boolean) {
        dateTimeStr = newValue
        showTimePicker.value = isTypeStart
    }

    fun onTimeSelected(newValue: String, isTypeStart: Boolean) {
        dateTimeStr += ", $newValue"
        if (isTypeStart)
            setStartDateTime.value = dateTimeStr
        else
            setEndDateTime.value = dateTimeStr
    }

    fun onTimeCanceled(isTypeStart: Boolean) {
        clearDateTime()
        showDatePicker.value = isTypeStart
    }

    private fun clearDateTime() {
        dateTimeStr = ""
        timeStr = ""
    }

    fun doShowDatePicker(): LiveData<Boolean> = showDatePicker
    fun doShowTimePicker(): LiveData<Boolean> = showTimePicker
    fun doSetStartDateTime(): LiveData<String> = setStartDateTime
    fun doSetEndDateTime(): LiveData<String> = setEndDateTime

}