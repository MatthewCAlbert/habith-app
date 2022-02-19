package com.bncc.habith.ui.addedit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityAddEditBinding
import com.bncc.habith.util.extension.createHandleDatePicker
import com.bncc.habith.util.extension.createTimePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker

class AddEditActivity : AppCompatActivity() {
    private lateinit var addEditBinding: ActivityAddEditBinding
    private val viewModel: AddEditViewModel by viewModels()

    private lateinit var datePickerStart: MaterialDatePicker<Long>
    private lateinit var datePickerEnd: MaterialDatePicker<Long>
    private lateinit var timePickerStart: MaterialTimePicker
    private lateinit var timePickerEnd: MaterialTimePicker

    private lateinit var submitTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        prepareActivity()
        setupView()
        subscribeLiveData()
    }

    private fun initBinding(){
        addEditBinding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(addEditBinding.root)
    }

    private fun setupView(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        timePickerStart = createTimePicker(R.string.start_time_picker.toString(),
            onGetResult = {
                viewModel.onTimeSelected(it, true)
            },
            onCancel = {
                viewModel.onTimeCanceled(true)
            }
        )
        timePickerEnd = createTimePicker(R.string.end_time_picker.toString(),
            onGetResult = {
                viewModel.onTimeSelected(it, false)
            },
            onCancel = {
                viewModel.onTimeCanceled(false)
            }
        )

        datePickerStart = createHandleDatePicker(R.string.start_date_picker.toString(),
            onGetResult = {
                viewModel.onDateSelected(it, true)
            }
        )
        datePickerEnd = createHandleDatePicker(R.string.end_date_picker.toString(),
            onGetResult = {
                viewModel.onDateSelected(it, false)
            }
        )

        addEditBinding.etHabitStartDateTime.setOnClickListener {
            viewModel.onClickDateTimeEditText(true)
        }

        addEditBinding.etHabitEndDateTime.setOnClickListener {
            viewModel.onClickDateTimeEditText(false)
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
            //make new habit or update existing habit
        }
    }

    private fun subscribeLiveData() {
        with(viewModel) {
            doShowDatePicker().observe(this@AddEditActivity) {
                if (it) {
                    datePickerStart.show(supportFragmentManager, "DatePicker")
                } else {
                    datePickerEnd.show(supportFragmentManager, "DatePicker")
                }
            }
            doShowTimePicker().observe(this@AddEditActivity) {
                if (it) {
                    timePickerStart.show(supportFragmentManager, "TimePicker")
                } else {
                    timePickerEnd.show(supportFragmentManager, "TimePicker")
                }
            }
            doSetStartDateTime().observe(this@AddEditActivity) {
                addEditBinding.etHabitStartDateTime.setText(it)
            }
            doSetEndDateTime().observe(this@AddEditActivity) {
                addEditBinding.etHabitEndDateTime.setText(it)
            }
        }
    }

    private fun prepareActivity(){
        val extras = intent.extras
        if(extras == null){
            addEditBinding.tvTitle.text = getString(R.string.add_habit_title)
            addEditBinding.btnSubmit.text = getString(R.string.add_habit_submit)
            submitTxt = R.string.add_habit_success.toString()
            addEditBinding.tvTitle.text = getString(R.string.add_habit_title)
            addEditBinding.btnSubmit.text = getString(R.string.add_habit_submit)
            submitTxt = getString(R.string.add_habit_success)
        }else{
            addEditBinding.tvTitle.text = getString(R.string.edit_habit_title)
            addEditBinding.etHabitName.setText(extras.getString("habitName", ""))
            addEditBinding.etHabitCats.setText(extras.getString("habitCats", ""))
            addEditBinding.etHabitStartDateTime.setText(extras.getString("startDateTime", ""))
            addEditBinding.etHabitEndDateTime.setText(extras.getString("endDateTime", ""))
            addEditBinding.etHabitReminder.setText(extras.getString("habitReminder", ""))
            addEditBinding.etHabitDesc.setText(extras.getString("habitDesc", ""))
            addEditBinding.tvTargetType.setText(extras.getString("targetType", "None"))
            addEditBinding.etTargetNum.setText(extras.getString("targetNum", ""))
            addEditBinding.btnSubmit.text = getString(R.string.edit_habit_submit)
            submitTxt = getString(R.string.edit_habit_success)
        }
    }
}