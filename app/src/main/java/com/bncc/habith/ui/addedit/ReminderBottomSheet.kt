package com.bncc.habith.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.bncc.habith.R
import com.bncc.habith.databinding.ItemReminderSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class ReminderBottomSheet : BottomSheetDialogFragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.item_reminder_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val typeSwitch = view.findViewById<SwitchMaterial>(R.id.switch_repeatType)
        val type1 = view.findViewById<LinearLayout>(R.id.layout_repeatType1)
        val etDayNum = view.findViewById<EditText>(R.id.et_repeatDayNum)
        val type2 = view.findViewById<LinearLayout>(R.id.layout_repeatType2)
        val btnSun = view.findViewById<Button>(R.id.btn_day7)
        val btnMon = view.findViewById<Button>(R.id.btn_day1)
        val btnTue = view.findViewById<Button>(R.id.btn_day2)
        val btnWed = view.findViewById<Button>(R.id.btn_day3)
        val btnThu = view.findViewById<Button>(R.id.btn_day4)
        val btnFri = view.findViewById<Button>(R.id.btn_day5)
        val btnSat = view.findViewById<Button>(R.id.btn_day6)
        val btnTime = view.findViewById<Button>(R.id.btn_repeatTime)
        val btnSubmit = view.findViewById<Button>(R.id.btn_submitReminder)

        typeSwitch.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked){
                type1.visibility = View.GONE
                type2.visibility = View.VISIBLE
            }else{
                type1.visibility = View.VISIBLE
                type2.visibility = View.GONE
            }
        }

        //are buttons a good choice for day picker?
        btnSun.setOnClickListener(this)
        btnMon.setOnClickListener(this)
        btnTue.setOnClickListener(this)
        btnWed.setOnClickListener(this)
        btnThu.setOnClickListener(this)
        btnFri.setOnClickListener(this)
        btnSat.setOnClickListener(this)

        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select reminder time")
                .build()

        btnTime.setOnClickListener {
            timePicker.show(parentFragmentManager, "TimePicker")
        }
    }


    companion object {
        const val TAG = "ReminderBottomSheet"
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}