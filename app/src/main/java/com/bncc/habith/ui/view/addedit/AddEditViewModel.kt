package com.bncc.habith.ui.view.addedit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.response.HabithResponse2
import com.bncc.habith.data.repository.HabithRepository
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repo: HabithRepository
): ViewModel() {
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
        dateTimeStr += " $newValue"
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

    private val _viewState = MutableLiveDataStatus<HabithResponse2>()

    fun create(data: HabithResponse2.Data) = viewModelScope.launch {
        _viewState.postLoading()
        try {
            val response = repo.createHabith(data)

            if(response!!.success) _viewState.postSuccess(response)
            else _viewState.postEmpty()
        }catch (e: Exception){
            _viewState.postError(e)
        }
    }

    fun viewState(): LiveDataStatus<HabithResponse2> = _viewState

    fun doShowDatePicker(): LiveData<Boolean> = showDatePicker
    fun doShowTimePicker(): LiveData<Boolean> = showTimePicker
    fun doSetStartDateTime(): LiveData<String> = setStartDateTime
    fun doSetEndDateTime(): LiveData<String> = setEndDateTime
}