package com.bncc.habith.ui.view.updateprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.databinding.ActivityUpdateProfileBinding
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.util.InputHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateProfileBinding
    private val viewModel: UpdateProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        setupInput()
        initObserver()
    }

    private fun initObserver() {
        viewModel.viewState().observe(this) {
            binding.viewState = it.status

            if (it.status == DataStatus.Status.SUCCESS) finish()
            else if (it.status == DataStatus.Status.ERROR) Toast.makeText(
                this,
                it.error.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initView() {
        with(binding) {
            editTextName.setText(intent.getStringExtra(KEY))

            binding.btnUpdate.setOnClickListener {
                if (!InputHelper.inputIsEmpty(
                        editTextName,
                        inputLayoutName,
                        this@UpdateProfileActivity
                    )
                ) viewModel.updateProfile(editTextName.text.toString())
            }
        }
    }

    private fun setupInput() {
        with(binding) {
            editTextName.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutName.isErrorEnabled)
                    inputLayoutName.isErrorEnabled = false
            }
        }
    }

    companion object {
        const val KEY = "to-update-detail"
    }
}