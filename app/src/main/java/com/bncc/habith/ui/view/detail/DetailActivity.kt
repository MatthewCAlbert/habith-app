package com.bncc.habith.ui.view.detail

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bncc.habith.R
import com.bncc.habith.data.remote.response.HabithResponse
import com.bncc.habith.databinding.ActivityDetailBinding
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.ui.view.addedit.AddEditActivity
import com.bncc.habith.util.InputHelper.fixedDate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var extras: HabithResponse.Data

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        getHabitFromIntent()
        setupView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.viewState().observe(this){
            detailBinding.viewState = it.status
            when(it.status){
                DataStatus.Status.SUCCESS -> {
                    Toast.makeText(this, "Habit ended!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                DataStatus.Status.ERROR -> Toast.makeText(this, "Remove Failed!", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        }
    }

    private fun initBinding() {
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailBinding.btnTargetPlus.setOnClickListener {
            detailBinding.etTargetNum.setText(viewModel.increaseTargetProgress().toString())
        }
        detailBinding.btnTargetMinus.setOnClickListener {
            detailBinding.etTargetNum.setText(viewModel.decreaseTargetProgress().toString())
        }

        detailBinding.btnDoneHabit.setOnClickListener {
            Toast.makeText(this, "Habit done for the day. Nice!", Toast.LENGTH_SHORT).show()
            finish()
        }
        detailBinding.btnActionHabit.setOnClickListener {
            viewModel.remove(extras.id!!)
            if (detailBinding.btnActionHabit.text == getString(R.string.detail_end_habit)) {
                detailBinding.btnActionHabit.text = getString(R.string.detail_start_habit)
            } else {
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
        if (item.itemId == R.id.menu_edit) {
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra(AddEditActivity.KEY, extras)
            intent.putExtra(AddEditActivity.TYPE, "edit")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getHabitFromIntent() {
        extras = intent.getParcelableExtra(KEY)!!
        detailBinding.tvHabitName.text = extras.title
        detailBinding.tvHabitCats.text = extras.category
        detailBinding.tvStartDateTimeValue.text = fixedDate(extras.start.toString())
        detailBinding.tvEndDateTimeValue.text = fixedDate(extras.end.toString())
//        detailBinding.tvReminderValue.text =
        detailBinding.tvDescValue.text = extras.description
        viewModel.targetType = extras.target_type
        viewModel.targetNum = extras.target
        if (viewModel.targetNum == 0) {
            //hide target progress section
            detailBinding.dividerTarget.visibility = View.GONE
            detailBinding.tvTargetTitle.visibility = View.GONE
            detailBinding.etLayoutTargetNum.visibility = View.GONE
            detailBinding.btnTargetPlus.visibility = View.GONE
            detailBinding.btnTargetMinus.visibility = View.GONE
        } else {
            detailBinding.etTargetNum.setText(viewModel.targetProgress.toString())
        }
    }

    companion object {
        const val KEY = "to-detail"
    }
}