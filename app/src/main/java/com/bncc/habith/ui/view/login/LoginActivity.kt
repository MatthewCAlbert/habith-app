package com.bncc.habith.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.databinding.ActivityLoginBinding
import com.bncc.habith.ui.state.DataStatus
import com.bncc.habith.ui.view.home.HomeActivity
import com.bncc.habith.ui.view.register.RegisterActivity
import com.bncc.habith.util.InputHelper.inputIsEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        initBinding()
        initView()
        setupInput()
        initObserver()

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
                    "Invalid username or password!",
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
        binding.buttonLogin.setOnClickListener {
            if (!inputIsEmpty(binding.editTextUsername, binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword, this)
            ) {
                val username = binding.editTextUsername.text.toString()
                val password = binding.editTextPassword.text.toString()
                viewModel.login(username, password)
            }
        }

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun setupInput() {
        with(binding) {
            editTextUsername.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutUsername.isErrorEnabled)
                    inputLayoutUsername.isErrorEnabled = false
            }

            editTextPassword.doOnTextChanged { _, _, _, _ ->
                if (inputLayoutPassword.isErrorEnabled)
                    inputLayoutPassword.isErrorEnabled = false
            }
        }
    }

    private fun initBinding() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}