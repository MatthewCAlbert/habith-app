package com.bncc.habith.ui.home.viewmodel

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.remote.HabithRepository
import com.bncc.habith.data.remote.response.HabithResponse
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AllViewModel : ViewModel() {

    private val repository = HabithRepository()

    private val c = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    private val df = SimpleDateFormat("dd MMMM, yyy")

    private var formattedDate = df.format(c.time)

    private val habithLiveData = MutableLiveData<List<HabithResponse>>()

    private val dateLiveData = MutableLiveData(formattedDate)

    fun fetchHabith() {
        viewModelScope.launch {
            val response = repository.getHabithOngoing()

            if (response.isNotEmpty())
                habithLiveData.value = response
        }
    }

    fun pickDate(context: Context) {
        val newYear = c.get(Calendar.YEAR)
        val newMonth = c.get(Calendar.MONTH)
        val newDay = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(context, { _, year, monthOfYear, dayOfMonth ->
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, monthOfYear)
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            formattedDate = df.format(c.time)

            dateLiveData.value = formattedDate
        }, newYear, newMonth, newDay).show()
    }

    fun pickDate(i: Int) {
        c.add(Calendar.DATE, i)
        formattedDate = df.format(c.time)

        dateLiveData.value = formattedDate
    }

    fun getHabith(): LiveData<List<HabithResponse>> = habithLiveData

    fun getDate(): LiveData<String> = dateLiveData
}