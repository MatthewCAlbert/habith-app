package com.bncc.habith.ui.addedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.bncc.habith.R
import com.bncc.habith.databinding.ItemReminderSheetBinding
import com.bncc.habith.util.extension.createTimePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReminderBottomSheet : BottomSheetDialogFragment(), CompoundButton.OnCheckedChangeListener {
    private lateinit var reminderBinding: ItemReminderSheetBinding
    private val viewModel: ReminderBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reminderBinding = ItemReminderSheetBinding.inflate(layoutInflater, container, false)
        return reminderBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reminderBinding.switchRepeatType.setOnCheckedChangeListener { button, isChecked ->
            if(isChecked){
                reminderBinding.layoutRepeatType1.visibility = View.GONE
                reminderBinding.layoutRepeatType2.visibility = View.VISIBLE
            }else{
                reminderBinding.layoutRepeatType1.visibility = View.VISIBLE
                reminderBinding.layoutRepeatType2.visibility = View.GONE
            }
        }

        reminderBinding.btnDay7.setOnCheckedChangeListener(this)
        reminderBinding.btnDay1.setOnCheckedChangeListener(this)
        reminderBinding.btnDay2.setOnCheckedChangeListener(this)
        reminderBinding.btnDay3.setOnCheckedChangeListener(this)
        reminderBinding.btnDay4.setOnCheckedChangeListener(this)
        reminderBinding.btnDay5.setOnCheckedChangeListener(this)
        reminderBinding.btnDay6.setOnCheckedChangeListener(this)

        val timePicker = createTimePicker(
            R.string.reminder_time_title.toString(),
            onGetResult = {
                reminderBinding.btnRepeatTime.text = it
            },
            onCancel = {

            }
        )

        reminderBinding.btnRepeatTime.setOnClickListener {
            timePicker.show(parentFragmentManager, "TimePicker")
        }

        reminderBinding.btnSubmitReminder.setOnClickListener {
            //pass bundle back to activity
            bundle.putString("repeatNum", reminderBinding.etRepeatDayNum.text.toString())
            dismiss()
        }
    }

    companion object {
        const val TAG = "ReminderBottomSheet"
    }

    var bundle = Bundle()

    override fun onCheckedChanged(view: CompoundButton, isChecked: Boolean) {
        if(view.id == R.id.btn_day7){
            if(isChecked){
                Toast.makeText(context, "Sunday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("sun", "Sun")
            }else{
                Toast.makeText(context, "Sunday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("sun")
            }
        }else if(view.id == R.id.btn_day1){
            if(isChecked){
                Toast.makeText(context, "Monday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("mon", "Mon")
            }else{
                Toast.makeText(context, "Monday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("mon")
            }
        }else if(view.id == R.id.btn_day2){
            if(isChecked){
                Toast.makeText(context, "Tuesday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("tue", "Tue")
            }else{
                Toast.makeText(context, "Tuesday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("tue")
            }
        }else if(view.id == R.id.btn_day3){
            if(isChecked){
                Toast.makeText(context, "Wednesday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("wed", "Wed")
            }else{
                Toast.makeText(context, "Wednesday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("wed")
            }
        }else if(view.id == R.id.btn_day4){
            if(isChecked){
                Toast.makeText(context, "Thursday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("thu", "Thu")
            }else{
                Toast.makeText(context, "Thursday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("thu")
            }
        }else if(view.id == R.id.btn_day5){
            if(isChecked){
                Toast.makeText(context, "Friday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("fri", "Fri")
            }else{
                Toast.makeText(context, "Friday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("fri")
            }
        }else if(view.id == R.id.btn_day6){
            if(isChecked){
                Toast.makeText(context, "Saturday selected", Toast.LENGTH_SHORT).show()
                bundle.putString("sat", "Sat")
            }else{
                Toast.makeText(context, "Saturday not selected", Toast.LENGTH_SHORT).show()
                bundle.remove("sat")
            }
        }
    }
}