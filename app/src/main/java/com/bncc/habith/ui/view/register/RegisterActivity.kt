package com.bncc.habith.ui.view.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityRegisterBinding
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.ui.view.home.HomeActivity
import com.bncc.habith.util.InputHelper.inputIsEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initBinding()
        initView()
        initObserver()
        setupInput()

    }

    private fun initObserver() {
        viewModel.viewState().observe(this) {
            binding.viewState = it.status
            when (it.status) {
                DataStatus.Status.SUCCESS -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
                DataStatus.Status.EMPTY -> Toast.makeText(
                    this,
                    "Email & username already taken!",
                    Toast.LENGTH_SHORT
                ).show()
                DataStatus.Status.ERROR -> Toast.makeText(
                    this,
                    it.error.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {/*do nothing!*/}
            }
        }
    }

    private fun initView() {
        binding.buttonRegister.setOnClickListener {
            if (!inputIsEmpty(binding.editTextName, binding.inputLayoutName, this)
                && !inputIsEmpty(binding.editTextUsername, binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextEmail, binding.inputLayoutEmail, this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword, this)
                && !inputIsEmpty(binding.editTextPasswordRepeat, binding.inputLayoutPasswordRepeat,this)
            ) {
                val username = binding.editTextUsername.text.toString()
                val name = binding.editTextName.text.toString()
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()
                val passwordRepeat = binding.editTextPasswordRepeat.text.toString()

                if (password == passwordRepeat) {
                    viewModel.register(name, email, password, username)
                } else {
                    Toast.makeText(this, R.string.password_not_matches, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textLogin.setOnClickListener {
            finish()
        }
    }

    private fun setupInput() {
        with(binding) {
            editTextUsername.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutUsername.isErrorEnabled)
                    inputLayoutUsername.isErrorEnabled = false
            }

            editTextName.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutName.isErrorEnabled)
                    inputLayoutName.isErrorEnabled = false
            }

            editTextEmail.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutEmail.isErrorEnabled)
                    inputLayoutEmail.isErrorEnabled = false
            }

            editTextPassword.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutPassword.isErrorEnabled)
                    inputLayoutPassword.isErrorEnabled = false
            }

            editTextPasswordRepeat.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutPasswordRepeat.isErrorEnabled)
                    inputLayoutPasswordRepeat.isErrorEnabled = false
            }
        }
    }

    private fun initBinding() {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}