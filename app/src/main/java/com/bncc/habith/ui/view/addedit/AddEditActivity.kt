package com.bncc.habith.ui.view.addedit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.data.remote.response.HabithResponse2
import com.bncc.habith.databinding.ActivityAddEditBinding
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.util.InputHelper.fixedDate
import com.bncc.habith.util.InputHelper.fixedDate2
import com.bncc.habith.util.InputHelper.inputIsEmpty
import com.bncc.habith.util.extension.createHandleDatePicker
import com.bncc.habith.util.extension.createTimePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditActivity : AppCompatActivity() {
    private lateinit var addEditBinding: ActivityAddEditBinding
    private val viewModel: AddEditViewModel by viewModels()
    private lateinit var extras: HabithResponse.Data

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

    private fun initBinding() {
        addEditBinding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(addEditBinding.root)
    }

    private fun setupView() {
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

//        val reminderBottomSheet = ReminderBottomSheet()
//        addEditBinding.etHabitReminder.setOnClickListener {
//            reminderBottomSheet.show(supportFragmentManager, reminderBottomSheet.tag)
//        }

        addEditBinding.btnSubmit.setOnClickListener {
            with(addEditBinding) {
                if (etHabitStartDateTime.text.toString().isEmpty()
                    && etHabitEndDateTime.text.toString().isEmpty()
                ) {
                    viewModel.create(
                        HabithResponse2.Data(
                            title = etHabitName.text.toString(),
                            category = etHabitCats.text.toString(),
                            description = etHabitDesc.text.toString(),
                            target = etTargetNum.text.toString().toInt(),
                            repeat_every_day = etHabitReminder.text.toString()
                        )
                    )
                } else {
                    viewModel.create(
                        HabithResponse2.Data(
                            title = etHabitName.text.toString(),
                            category = etHabitCats.text.toString(),
                            description = etHabitDesc.text.toString(),
                            target = etTargetNum.text.toString().toInt(),
                            start = etHabitStartDateTime.text.toString(),
                            end = etHabitEndDateTime.text.toString(),
                            repeat_every_day = etHabitReminder.text.toString()
                        )
                    )
                }
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
                addEditBinding.etHabitStartDateTime.setText(fixedDate2(it))
            }
            doSetEndDateTime().observe(this@AddEditActivity) {
                addEditBinding.etHabitEndDateTime.setText(fixedDate2(it))
            }
            viewState().observe(this@AddEditActivity) {
                addEditBinding.viewState = it.status
                when (it.status) {
                    DataStatus.Status.SUCCESS -> {
                        Toast.makeText(
                            this@AddEditActivity,
                            "Create habith success!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                    DataStatus.Status.ERROR -> Toast.makeText(
                        this@AddEditActivity,
                        it.error.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {}
                }
            }
        }
    }

    private fun prepareActivity() {
        val type = intent.getStringExtra(TYPE)
        if (type.equals("add")) {
            addEditBinding.tvTitle.text = getString(R.string.add_habit_title)
            addEditBinding.btnSubmit.text = getString(R.string.add_habit_submit)
            submitTxt = R.string.add_habit_success.toString()
        } else {
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

    companion object {
        const val KEY = "to-add-edit"
        const val TYPE = "input-type"
    }
}