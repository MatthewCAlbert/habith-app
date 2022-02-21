package com.bncc.habith.ui.view.addedit

import android.content.Context
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

    private var listener: ReminderBottomSheetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reminderBinding = ItemReminderSheetBinding.inflate(layoutInflater, container, false)
        return reminderBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ReminderBottomSheetListener){
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
            getString(R.string.reminder_time_title),
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
            if(reminderBinding.switchRepeatType.isChecked){
                viewModel.sortDayArr()
            }else{
                reminderBinding.etRepeatDayNum.text.toString()
            }
            listener?.onSubmitReminder()
            dismiss()
        }
    }

    override fun onCheckedChanged(view: CompoundButton, isChecked: Boolean) {
        if(view.id == R.id.btn_day7){
            if(isChecked){
                viewModel.addDay(6)
            }else{
                viewModel.removeDay(6)
            }
        }else if(view.id == R.id.btn_day1){
            if(isChecked){
                viewModel.addDay(0)
            }else{
                viewModel.removeDay(0)
            }
        }else if(view.id == R.id.btn_day2){
            if(isChecked){
                viewModel.addDay(1)
            }else{
                viewModel.removeDay(1)
            }
        }else if(view.id == R.id.btn_day3){
            if(isChecked){
                viewModel.addDay(2)
            }else{
                viewModel.removeDay(2)
            }
        }else if(view.id == R.id.btn_day4){
            if(isChecked){
                viewModel.addDay(3)
            }else{
                viewModel.removeDay(3)
            }
        }else if(view.id == R.id.btn_day5){
            if(isChecked){
                viewModel.addDay(4)
            }else{
                viewModel.removeDay(4)
            }
        }else if(view.id == R.id.btn_day6){
            if(isChecked){
                viewModel.addDay(5)
            }else{
                viewModel.removeDay(5)
            }
        }
    }
}