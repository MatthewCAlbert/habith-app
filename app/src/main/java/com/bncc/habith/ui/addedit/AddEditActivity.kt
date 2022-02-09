package com.bncc.habith.ui.addedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityAddEditBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

class AddEditActivity : AppCompatActivity() {
    private lateinit var addEditBinding: ActivityAddEditBinding
    var submitTxt: String? = "Habit created successfully"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addEditBinding = ActivityAddEditBinding.inflate(layoutInflater)
        val view = addEditBinding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        prepareActivity(extras)

        //datePickers
        var dateTimeStr = ""

        val datePickerStart =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select start date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        val datePickerEnd =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select end date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        //timePickers
        var timeStr = ""

        val timePickerStart =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select start time")
                .build()

        val timePickerEnd =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select end time")
                .build()

        datePickerStart.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd/MM/yy")
            val date = dateFormatter.format(Date(it))
            dateTimeStr = date
            timePickerStart.show(supportFragmentManager, "TimePicker")
        }

        datePickerEnd.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd/MM/yy")
            val date = dateFormatter.format(Date(it))
            dateTimeStr = date
            timePickerEnd.show(supportFragmentManager, "TimePicker")
        }

        timePickerStart.addOnPositiveButtonClickListener {
            val hour = timePickerStart.hour
            val minute = timePickerStart.minute

            val formattedTime =
                if(minute < 10)
                    "${hour}:0${minute}"
                else
                    "${hour}:${minute}"

            timeStr = formattedTime
            dateTimeStr += ", $timeStr"
            addEditBinding.etHabitStartDateTime.setText(dateTimeStr)
        }

        timePickerEnd.addOnPositiveButtonClickListener {
            val hour = timePickerEnd.hour
            val minute = timePickerEnd.minute

            val formattedTime =
                if(minute < 10)
                    "${hour}:0${minute}"
                else
                    "${hour}:${minute}"

            timeStr = formattedTime
            dateTimeStr += ", $timeStr"
            addEditBinding.etHabitEndDateTime.setText(dateTimeStr)
        }

        timePickerStart.addOnNegativeButtonClickListener {
            datePickerStart.show(supportFragmentManager, "DatePicker")
        }

        timePickerEnd.addOnNegativeButtonClickListener {
            datePickerEnd.show(supportFragmentManager, "DatePicker")
        }

        addEditBinding.etHabitStartDateTime.setOnClickListener {
            dateTimeStr = ""
            timeStr = ""
            datePickerStart.show(supportFragmentManager, "DatePicker")
        }

        addEditBinding.etHabitEndDateTime.setOnClickListener {
            dateTimeStr = ""
            timeStr = ""
            datePickerEnd.show(supportFragmentManager, "DatePicker")
        }

        val targetTypes = listOf("None", "Equal", "Less than equal", "More than equal")
        val targetAdapter = ArrayAdapter(this, R.layout.list_target_type, targetTypes)
        addEditBinding.tvTargetType.setAdapter(targetAdapter)
        addEditBinding.tvTargetType.setText(targetTypes[0], false)

        val reminderBottomSheet = ReminderBottomSheet()

        addEditBinding.etHabitReminder.setOnClickListener {
            reminderBottomSheet.show(supportFragmentManager, reminderBottomSheet.tag)
        }

        addEditBinding.btnSubmit.setOnClickListener {
            Toast.makeText(this, submitTxt, Toast.LENGTH_SHORT).show()
        }
    }

    fun prepareActivity(extras: Bundle?){
        if(extras == null){
            addEditBinding.tvTitle.text = "Add New Habit"
            addEditBinding.btnSubmit.text = "Create this habit"
        }else{
            addEditBinding.tvTitle.text = "Edit Habit Info"
            addEditBinding.etHabitName.setText(extras.getString("habitName", ""))
            addEditBinding.etHabitCats.setText(extras.getString("habitCats", ""))
            addEditBinding.etHabitStartDateTime.setText(extras.getString("startDateTime", ""))
            addEditBinding.etHabitEndDateTime.setText(extras.getString("endDateTime", ""))
            addEditBinding.etHabitReminder.setText(extras.getString("habitReminder", ""))
            addEditBinding.etHabitDesc.setText(extras.getString("habitDesc", ""))
            addEditBinding.tvTargetType.setText(extras.getString("targetType", "None"))
            addEditBinding.etTargetNum.setText(extras.getString("targetNum", ""))
            addEditBinding.btnSubmit.text = "Update habit info"
            submitTxt = ""
        }
    }
}