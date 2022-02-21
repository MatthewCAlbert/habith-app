package com.bncc.habith.ui.view.updatepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityUpdatePasswordBinding
import com.bncc.habith.ui.view.updateprofile.UpdateProfileActivity
import com.bncc.habith.util.InputHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdatePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePasswordBinding
    private val viewModel: UpdatePasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdatePasswordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        setupInput()
        initObserver()
    }

    private fun initObserver() {
        viewModel.viewState().observe(this) {
            when (it) {
                "loading" -> {
                    binding.btnUpdate.isEnabled = false
                    binding.loading.isVisible = true
                }
                "success" -> finish()
                "failed" -> {
                    binding.btnUpdate.isEnabled = true
                    binding.loading.isVisible = false
                    Toast.makeText(this, "Update failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initView() = with(binding) {
        btnUpdate.setOnClickListener {
            if (!InputHelper.inputIsEmpty(editTextOld, inputLayoutOld, this@UpdatePasswordActivity)
                && !InputHelper.inputIsEmpty(
                    editTextNew,
                    inputLayoutNew,
                    this@UpdatePasswordActivity
                )
                && !InputHelper.inputIsEmpty(editTextRe, inputLayoutRe, this@UpdatePasswordActivity)
            ) {
                val old = editTextOld.text.toString()
                val new = editTextNew.text.toString()

                if (editTextNew.text.toString() == editTextRe.text.toString()) {
                    viewModel.updatePassword(old, new)
                } else
                    Toast.makeText(
                        this@UpdatePasswordActivity,
                        R.string.password_not_matches,
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun setupInput() {
        with(binding) {
            editTextNew.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutNew.isErrorEnabled)
                    inputLayoutNew.isErrorEnabled = false
            }

            editTextOld.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutOld.isErrorEnabled)
                    inputLayoutOld.isErrorEnabled = false
            }

            editTextRe.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutRe.isErrorEnabled)
                    inputLayoutRe.isErrorEnabled = false
            }

        }
    }

}