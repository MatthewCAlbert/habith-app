package com.bncc.habith.ui.view.addedit

import androidx.lifecycle.ViewModel

class ReminderBottomSheetViewModel: ViewModel() {
    private var dayArr: ArrayList<Int>? = null

    fun sortDayArr(){
        dayArr?.sort()
    }

    fun addDay(dayNum: Int){
        dayArr?.add(dayNum)
    }

    fun removeDay(dayNum: Int){
        dayArr?.remove(dayNum)
    }
}