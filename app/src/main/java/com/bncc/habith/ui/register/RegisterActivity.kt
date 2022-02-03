package com.bncc.habith.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.databinding.ActivityRegisterBinding
import com.bncc.habith.ui.login.LoginActivity
import com.bncc.habith.ui.main.MainActivity
import com.bncc.habith.util.InputHelper
import com.bncc.habith.util.InputHelper.inputIsEmpty

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initBinding()
        setupInput()

        binding.buttonRegister.setOnClickListener {
            if (!inputIsEmpty(binding.editTextUsername,binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextEmail, binding.inputLayoutEmail,this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword,this)
                && !inputIsEmpty(binding.editTextPasswordRepeat, binding.inputLayoutPasswordRepeat,this)
            ) {
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
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