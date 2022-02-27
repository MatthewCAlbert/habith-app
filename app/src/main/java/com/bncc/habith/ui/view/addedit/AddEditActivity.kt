package com.bncc.habith.ui.view.addedit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ActivityAddEditBinding
import com.bncc.habith.util.extension.createHandleDatePicker
import com.bncc.habith.util.extension.createTimePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker

class AddEditActivity : AppCompatActivity(), ReminderBottomSheetListener {
    private lateinit var addEditBinding: ActivityAddEditBinding
    private val viewModel: AddEditViewModel by viewModels()
    private lateinit var extras: HabithResponse

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

        timePickerStart = createTimePicker(getString(R.string.start_time_picker),
            onGetResult = {
                viewModel.onTimeSelected(it, true)
            },
            onCancel = {
                viewModel.onTimeCanceled(true)
            }
        )
        timePickerEnd = createTimePicker(getString(R.string.end_time_picker),
            onGetResult = {
                viewModel.onTimeSelected(it, false)
            },
            onCancel = {
                viewModel.onTimeCanceled(false)
            }
        )

        datePickerStart = createHandleDatePicker(getString(R.string.start_date_picker),
            onGetResult = {
                viewModel.onDateSelected(it, true)
            }
        )
        datePickerEnd = createHandleDatePicker(getString(R.string.end_date_picker),
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
            addEditBinding.etHabitName.text.toString()
            addEditBinding.etHabitCats.text.toString()
            addEditBinding.etHabitStartDateTime.text.toString()
            addEditBinding.etHabitEndDateTime.text.toString()
            addEditBinding.etHabitReminder.text.toString()
            addEditBinding.etHabitDesc.text.toString()
            addEditBinding.tvTargetType.text
            addEditBinding.etTargetNum.text.toString()
            if(addEditBinding.btnSubmit.text == getString(R.string.add_habit_submit)){
                //make new habit
            }else{
                //update existing habit
            }
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
        val type = intent.getStringExtra(TYPE)
        if(type.equals("add")){
            addEditBinding.tvTitle.text = getString(R.string.add_habit_title)
            addEditBinding.btnSubmit.text = getString(R.string.add_habit_submit)
            submitTxt = getString(R.string.add_habit_success)
        }else{
            extras = intent.getParcelableExtra(KEY)!!
            addEditBinding.tvTitle.text = getString(R.string.edit_habit_title)
            addEditBinding.etHabitName.setText(extras.title)
            addEditBinding.etHabitCats.setText(extras.category)
            addEditBinding.etHabitStartDateTime.setText(extras.start)
            addEditBinding.etHabitEndDateTime.setText(extras.end)
//            addEditBinding.etHabitReminder.setText(extras.getString("habitReminder", ""))
            addEditBinding.etHabitDesc.setText(extras.description)
            addEditBinding.tvTargetType.setText(extras.target_type)
            addEditBinding.etTargetNum.setText(extras.target.toString())
            addEditBinding.btnSubmit.text = getString(R.string.edit_habit_submit)
            submitTxt = getString(R.string.edit_habit_success)
        }
    }

    companion object{
        const val KEY = "to-add-edit"
        const val TYPE = "input-type"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSubmitReminder() {
        //how to get dayArr from ReminderViewModel to here?
    }
}