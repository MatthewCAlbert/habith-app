package com.bncc.habith.ui.view.habithall

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bncc.habith.data.repository.HabithRepositoryImpl
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.ui.state.LiveDataStatus
import com.bncc.habith.ui.state.MutableLiveDataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    private val repo: HabithRepositoryImpl
) : ViewModel() {

    private val c = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat")
    private val df = SimpleDateFormat("dd MMMM, yyy")

    private var formattedDate = df.format(c.time)

    private val dateLiveData = MutableLiveData(formattedDate)

    private val _habithLiveData = MutableLiveDataStatus<List<HabithResponse.Data>>()

    fun fetchHabith() = viewModelScope.launch {
        _habithLiveData.postLoading()
        try {
            val response = repo.getHabithAll()
            delay(1500)

            if (response!!.data.isNullOrEmpty()) _habithLiveData.postEmpty()
            else _habithLiveData.postSuccess(response.data!!)

        } catch (e: Exception) {
            _habithLiveData.postError(e)
        }
    }

    fun getHabith(): LiveDataStatus<List<HabithResponse.Data>> = _habithLiveData

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

    fun getDate(): LiveData<String> = dateLiveData
}