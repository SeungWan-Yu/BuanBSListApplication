package com.buan.buanbslistapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buan.buanbslistapplication.databinding.ActivityPwactivityBinding

class PWActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPwactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPwactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btcf.setOnClickListener {
            val pw = binding.etPW.text.toString()
            if (pw == "0523"){
                startActivity(Intent(this, MainActivity::class.java))
                App.prefs.check = 1
                finish()
            }
        }

    }
}