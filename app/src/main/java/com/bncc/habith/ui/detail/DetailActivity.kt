package com.bncc.habith.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ActivityDetailBinding
import com.bncc.habith.ui.addedit.AddEditActivity

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        getHabitFromIntent()
        setupView()
    }

    private fun initBinding(){
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
    }

    private fun setupView(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailBinding.btnTargetPlus.setOnClickListener {
            detailBinding.etTargetNum.setText(viewModel.increaseTargetProgress().toString())
        }
        detailBinding.btnTargetMinus.setOnClickListener {
            detailBinding.etTargetNum.setText(viewModel.decreaseTargetProgress().toString())
        }

        detailBinding.btnDoneHabit.setOnClickListener {
            Toast.makeText(this, "Habit done for the day. Nice!", Toast.LENGTH_SHORT).show()
        }
        detailBinding.btnActionHabit.setOnClickListener {
            if(detailBinding.btnActionHabit.text == getString(R.string.detail_end_habit)){
                Toast.makeText(this, "Habit ended!", Toast.LENGTH_SHORT).show()
                detailBinding.btnActionHabit.text = getString(R.string.detail_start_habit)
            }else{
                Toast.makeText(this, "Habit started!", Toast.LENGTH_SHORT).show()
                detailBinding.btnActionHabit.text = getString(R.string.detail_end_habit)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_app_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_edit){
            val intent = Intent(this, AddEditActivity::class.java).apply {
                putExtra("habitName", detailBinding.tvHabitName.text)
                putExtra("habitCats", detailBinding.tvHabitCats.text)
                putExtra("startDateTime", detailBinding.tvStartDateTimeValue.text)
                putExtra("endDateTime", detailBinding.tvEndDateTimeValue.text)
                putExtra("habitReminder", detailBinding.tvReminderValue.text)
                putExtra("habitDesc", detailBinding.tvDescValue.text)
                putExtra("targetType", viewModel.targetType)
                putExtra("targetNum", viewModel.targetNum.toString())
            }
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getHabitFromIntent(){
        val extras = intent.getParcelableExtra<HabithResponse>("")!!
        detailBinding.tvHabitName.text = extras.title
        detailBinding.tvHabitCats.text = extras.category
        detailBinding.tvStartDateTimeValue.text = extras.start
        detailBinding.tvEndDateTimeValue.text = extras.end
        if(extras.repeat_every_day.isEmpty()){
            detailBinding.tvReminderValue.text = "Every " + extras.repeat_on
        }else if(extras.repeat_on?.isEmpty() == true){
            detailBinding.tvReminderValue.text = "Every " + extras.repeat_every_day
        }

        detailBinding.tvDescValue.text = extras.description
        viewModel.targetType = extras.target_type
        viewModel.targetNum = extras.target
        if(viewModel.targetNum==0){
            //hide target progress section
            detailBinding.dividerTarget.visibility = View.GONE
            detailBinding.tvTargetTitle.visibility = View.GONE
            detailBinding.etLayoutTargetNum.visibility = View.GONE
            detailBinding.btnTargetPlus.visibility = View.GONE
            detailBinding.btnTargetMinus.visibility = View.GONE
        }else {
            detailBinding.etTargetNum.setText(viewModel.targetProgress.toString())
        }
    }
}