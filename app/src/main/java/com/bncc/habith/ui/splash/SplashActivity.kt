package com.bncc.habith.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bncc.habith.databinding.ActivitySplashBinding
import com.bncc.habith.ui.addedit.AddEditActivity
import com.bncc.habith.ui.login.LoginActivity
import com.bncc.habith.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        initBinding()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
//            var intent = Intent(this, AddEditActivity::class.java).apply {
//                putExtra("habitName", "Do homework")
//                putExtra("habitCats", "Education")
//                putExtra("startDateTime", "07/02/22, 21:35")
//                putExtra("endDateTime", "25/02/22, 19:35")
//                putExtra("habitReminder", "Every Mon, Tue, Wed, Thu, Fri on 14:00")
//                putExtra("habitDesc", "Do your IT homework regularly")
//                putExtra("targetType", "More than equal")
//                putExtra("targetNum", "3")
//            }
//            startActivity(intent)
//            startActivity(Intent(this, AddEditActivity::class.java))
            finish()
        }, 2000) // hold within 2s
    }

    private fun initBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}