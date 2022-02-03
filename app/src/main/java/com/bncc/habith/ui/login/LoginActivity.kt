package com.bncc.habith.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityLoginBinding
import com.bncc.habith.ui.main.MainActivity
import com.bncc.habith.util.InputHelper.inputIsEmpty

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        initBinding()
        setupInput()

        binding.buttonLogin.setOnClickListener {
            if (!inputIsEmpty(binding.editTextUsername, binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword, this)
            ) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        binding.textRegister.setOnClickListener {
            // todo navigate to register activity
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