package com.buan.buanbslistapplication

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.buan.buanbslistapplication.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    var versionName = ""
    var response = ""


    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

            try {
                val pm = packageManager.getPackageInfo(this.packageName, 0)
                versionName = pm.versionName
            } catch (e : Exception) {
                // Exception
            }

        CoroutineScope(Dispatchers.Main).launch {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                response = UserRepo.version()[0].vs
            }

            if(versionName != response){
                val builder = AlertDialog.Builder(this@SplashActivity)
                    .setTitle("업데이트")
                    .setMessage("새로운 버전이 출시되었습니다\n업데이트 후 사용 가능합니다")
                    .setPositiveButton("확인"
                    ) { dialog, which ->
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("market://details?id=$packageName")
                        startActivity(intent)
                    }
                    .setNegativeButton("취소"
                    ) { dialog, which ->
                        finish()
                    }
                builder.show()
            }else{
                if (App.prefs.check == 1){ //비밀번호 저장된 상태
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }, 3000)
                }else{ //비밀번호 입력 액티비티로 이동
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, PWActivity::class.java))
                        finish()
                    }, 3000)
                }
            }
        }

    }
}