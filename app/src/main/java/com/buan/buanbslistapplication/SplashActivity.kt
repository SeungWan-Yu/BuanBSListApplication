package com.buan.buanbslistapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.buan.buanbslistapplication.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (App.prefs.check == 1){ //비밀번호 저장된 상태
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }else{ //비밀번호 입력 액티비티로 이동
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, PWActivity::class.java))
                finish()
            }, 3000)
        }
    }
}