package com.bncc.habith.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bncc.habith.databinding.ActivityHomeBinding
import com.bncc.habith.ui.home.fragment.AllFragment
import com.bncc.habith.ui.home.fragment.OngoingFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initView()
    }

    private fun initView() {
        val tabNames = listOf("Ongoing", "All")

        with(binding){
            pagerHome.adapter = object : FragmentStateAdapter(this@HomeActivity){
                override fun getItemCount(): Int = tabNames.size

                override fun createFragment(position: Int) = when(position){
                    0 -> OngoingFragment()
                    else -> AllFragment()
                }
            }

            TabLayoutMediator(tabHome, pagerHome){tab, position ->
                tab.text = tabNames[position]
            }.attach()

            fabHome.setOnClickListener {
                //todo navigate to EditActivity
            }
        }
    }

    private fun initBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}