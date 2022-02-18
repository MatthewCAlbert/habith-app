package com.bncc.habith.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.bncc.habith.R
import com.bncc.habith.databinding.ActivityRegisterBinding
import com.bncc.habith.ui.home.HomeActivity
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
        setupInput()

        binding.buttonRegister.setOnClickListener {
            if (!inputIsEmpty(binding.editTextUsername,binding.inputLayoutUsername, this)
                && !inputIsEmpty(binding.editTextEmail, binding.inputLayoutEmail,this)
                && !inputIsEmpty(binding.editTextPassword, binding.inputLayoutPassword,this)
                && !inputIsEmpty(binding.editTextPasswordRepeat, binding.inputLayoutPasswordRepeat,this)
            ) {
                val username = binding.editTextUsername.text.toString()
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()
                val passwordRepeat = binding.editTextPasswordRepeat.text.toString()

                if (password == passwordRepeat){
                    viewModel.register(username, email, password)
                }else{
                    Toast.makeText(this, R.string.password_not_matches, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getIsSuccess().observe(this){
            if (it){
                startActivity(Intent(this, HomeActivity::class.java))
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