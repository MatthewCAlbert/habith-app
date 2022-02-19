package com.bncc.habith.ui.view.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityLoginBinding
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
        setupInput()

        binding.buttonLogin.setOnClickListener {
            if (!inputIsEmpty(binding.editTextUsername, binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword, this)
            ) {
                val username = binding.editTextUsername.text.toString()
                val password = binding.editTextPassword.text.toString()
                viewModel.login(username, password)
                showLoading()
            }
        }

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.getIsSuccess().observe(this){
            if (it){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                hideLoading()
                Toast.makeText(this, R.string.auth_not_matches, Toast.LENGTH_SHORT).show()
            }
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
        hideLoading()
    }

    private fun hideLoading() = with(binding){
        buttonLogin.isEnabled = true
        loading.isVisible = false
    }

    private fun showLoading() = with(binding){
        buttonLogin.isEnabled = false
        loading.isVisible = true
    }

}