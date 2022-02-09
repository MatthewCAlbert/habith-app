package com.bncc.habith.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bncc.habith.R
import com.bncc.habith.databinding.ItemReminderSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class ReminderBottomSheet : BottomSheetDialogFragment(), CompoundButton.OnCheckedChangeListener {
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
        val btnTime = view.findViewById<Button>(R.id.btn_repeatTime)
        val btnDay7 = view.findViewById<MaterialCheckBox>(R.id.btn_day7)
        val btnDay1 = view.findViewById<MaterialCheckBox>(R.id.btn_day1)
        val btnDay2 = view.findViewById<MaterialCheckBox>(R.id.btn_day2)
        val btnDay3 = view.findViewById<MaterialCheckBox>(R.id.btn_day3)
        val btnDay4 = view.findViewById<MaterialCheckBox>(R.id.btn_day4)
        val btnDay5 = view.findViewById<MaterialCheckBox>(R.id.btn_day5)
        val btnDay6 = view.findViewById<MaterialCheckBox>(R.id.btn_day6)
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

        btnDay7.setOnCheckedChangeListener(this)
        btnDay1.setOnCheckedChangeListener(this)
        btnDay2.setOnCheckedChangeListener(this)
        btnDay3.setOnCheckedChangeListener(this)
        btnDay4.setOnCheckedChangeListener(this)
        btnDay5.setOnCheckedChangeListener(this)
        btnDay6.setOnCheckedChangeListener(this)

        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select reminder time")
                .build()

        timePicker.addOnPositiveButtonClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute
            val timeStr =
                if (minute < 10) {
                    "${hour}:0${minute}"
                } else {
                    "${hour}:${minute}"
                }
            btnTime.text = timeStr
        }

        btnTime.setOnClickListener {
            timePicker.show(parentFragmentManager, "TimePicker")
        }

        btnSubmit.setOnClickListener {
            dismiss()
        }
    }


    companion object {
        const val TAG = "ReminderBottomSheet"
    }

    override fun onCheckedChanged(view: CompoundButton, isChecked: Boolean) {
        if(view.id == R.id.btn_day7){
            if(isChecked){
                Toast.makeText(context, "Sunday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day1){
            if(isChecked){
                Toast.makeText(context, "Monday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day2){
            if(isChecked){
                Toast.makeText(context, "Tuesday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day3){
            if(isChecked){
                Toast.makeText(context, "Wednesday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day4){
            if(isChecked){
                Toast.makeText(context, "Thursday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day5){
            if(isChecked){
                Toast.makeText(context, "Friday selected", Toast.LENGTH_SHORT).show()
            }
        }else if(view.id == R.id.btn_day6){
            if(isChecked){
                Toast.makeText(context, "Saturday selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}