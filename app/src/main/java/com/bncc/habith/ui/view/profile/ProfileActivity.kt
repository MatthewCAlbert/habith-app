package com.bncc.habith.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityProfileBinding
import com.bncc.habith.ui.adapter.ProfileAdapter
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.ui.view.login.LoginActivity
import com.bncc.habith.ui.view.updatepassword.UpdatePasswordActivity
import com.bncc.habith.ui.view.updateprofile.UpdateProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var animationController: LayoutAnimationController

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
        initObserver()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getUserDetail()
    }

    private fun initView() {
        val images = resources.obtainTypedArray(R.array.profile_images)
        val texts = resources.getStringArray(R.array.profile_texts)
        animationController =
            AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)

        binding.recyclerProfile.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            layoutAnimation = animationController
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
            binding.viewState = it.status
            if (it.status == DataStatus.Status.SUCCESS){
                binding.textEmail.text = it.data!!.user.email
                binding.textName.text = it.data.user.name
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}