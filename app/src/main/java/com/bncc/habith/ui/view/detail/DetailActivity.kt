package com.bncc.habith.ui.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityDetailBinding
import com.bncc.habith.ui.view.addedit.AddEditActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var extras: HabithResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
        getHabitFromIntent()
        setupView()
    }

    private fun initBinding() {
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        viewModel.isSuccess().observe(this){
            if (it)
                finish()
        }
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
//            startActivity(Intent(this, HomeActivity::class.java))
        }
        detailBinding.btnActionHabit.setOnClickListener {
            viewModel.deleteHabith(extras.id!!)
            if (detailBinding.btnActionHabit.text == getString(R.string.detail_end_habit)) {
                Toast.makeText(this, "Habit ended!", Toast.LENGTH_SHORT).show()
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

    private fun getHabitFromIntent() {
        extras = intent.getParcelableExtra(KEY)!!
        detailBinding.tvHabitName.text = extras.title
        detailBinding.tvHabitCats.text = extras.category
        detailBinding.tvStartDateTimeValue.text = extras.start
        detailBinding.tvEndDateTimeValue.text = extras.end
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