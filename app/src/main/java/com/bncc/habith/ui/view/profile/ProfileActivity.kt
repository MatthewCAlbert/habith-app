package com.bncc.habith.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityProfileBinding
import com.bncc.habith.ui.adapter.ProfileAdapter
import com.bncc.habith.ui.view.login.LoginActivity
import com.bncc.habith.ui.view.updatepassword.UpdatePasswordActivity
import com.bncc.habith.ui.view.updateprofile.UpdateProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initObserver()
        initView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserDetail()
    }

    private fun initView() {
        val images = resources.obtainTypedArray(R.array.profile_images)
        val texts = resources.getStringArray(R.array.profile_texts)

        binding.recyclerProfile.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = ProfileAdapter(images, texts) {
                when (it) {
                    "Edit Profile" -> {
                        val i = Intent(this@ProfileActivity, UpdateProfileActivity::class.java)
                        i.putExtra(UpdateProfileActivity.KEY, binding.textName.text.toString())
                        startActivity(i)
                    }
                    "Edit Password" -> {
                        startActivity(
                            Intent(
                                this@ProfileActivity,
                                UpdatePasswordActivity::class.java
                            )
                        )
                    }
                    "Logout" -> {
                        viewModel.logout()
                        startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                        finishAffinity()
                    }
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.userDetail().observe(this) {
            binding.textEmail.text = it.data.user.email
            binding.textName.text = it.data.user.name
        }

        viewModel.viewState().observe(this) {
            when (it) {
                "loading" -> {}
                "success" -> {}
            }
        }
    }
}