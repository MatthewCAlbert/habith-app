package com.bncc.habith.ui.view.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityHomeBinding
import com.bncc.habith.ui.view.addedit.AddEditActivity
import com.bncc.habith.ui.view.habithall.AllFragment
import com.bncc.habith.ui.view.habithongoing.OngoingFragment
import com.bncc.habith.ui.view.profile.ProfileActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

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
            R.id.menu_setting -> {
                startActivity(Intent(this, ProfileActivity::class.java))
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
                val intent = Intent(this@HomeActivity, AddEditActivity::class.java)
                intent.putExtra(AddEditActivity.TYPE, "add")
                startActivity(intent)
            }
        }
    }

    private fun initBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}