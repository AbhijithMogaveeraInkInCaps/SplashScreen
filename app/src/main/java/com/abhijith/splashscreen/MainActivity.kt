package com.abhijith.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import com.abhijith.splashscreen.R
import com.abhijith.splashscreen.ui.IntroActivity
import com.abhijith.splashscreen.ui.view.group.MainView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        finish()
        findViewById<MainView>(R.id.ss).apply {
            setOnFinishCallBack {
                startActivity(Intent(context, IntroActivity::class.java))
                finish()
            }
        }
    }
}