package com.bncc.habith.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityHomeBinding
import com.bncc.habith.ui.addedit.AddEditActivity
import com.bncc.habith.ui.home.viewmodel.HomeViewModel
import com.bncc.habith.ui.login.LoginActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                viewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        val tabNames = listOf("Ongoing", "All")

        with(binding) {
            pagerHome.adapter = object : FragmentStateAdapter(this@HomeActivity) {
                override fun getItemCount(): Int = tabNames.size

                override fun createFragment(position: Int) = when (position) {
                    0 -> OngoingFragment()
                    else -> AllFragment()
                }
            }

            TabLayoutMediator(tabHome, pagerHome) { tab, position ->
                tab.text = tabNames[position]
            }.attach()

            fabHome.setOnClickListener {
                startActivity(Intent(this@HomeActivity, AddEditActivity::class.java))
            }
        }
    }

    private fun initBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}