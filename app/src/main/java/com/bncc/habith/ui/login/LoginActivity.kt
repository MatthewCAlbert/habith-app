package com.bncc.habith.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityLoginBinding
import com.bncc.habith.ui.main.MainActivity
import com.bncc.habith.ui.register.RegisterActivity
import com.bncc.habith.util.InputHelper.inputIsEmpty

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
            }
        }

        binding.textRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        viewModel.getIsSuccess().observe(this){
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
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
    }
}