package com.leahhumlelu.kotlinprogram

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Thread.sleep

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
        /**Thread(Runnable {
            try {
                sleep(4000)
            }catch (e:InterruptedException){
                e.printStackTrace()
            }finally {

            }
        }).start()**/
    }
}