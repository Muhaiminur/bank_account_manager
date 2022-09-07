package com.bankaccount.bankaccountmanager.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.bankaccount.bankaccountmanager.databinding.ActivityMainBinding
import com.bankaccount.bankaccountmanager.utitlity.Utility


class SplashPage : AppCompatActivity() {
    lateinit var utility: Utility
    lateinit var context: Context
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            context = this
            utility = Utility(context)
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
            hideSystemBars()
            object : CountDownTimer(5000, 1000) {
                override fun onTick(l: Long) {}
                override fun onFinish() {
                    startActivity(Intent(context, HomePage::class.java))
                }
            }.start()
        } catch (e: Exception) {
            Log.d("Error Line Number", Log.getStackTraceString(e))
        }

    }
    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }
}