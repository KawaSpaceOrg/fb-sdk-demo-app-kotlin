package com.skechers.kotlinsampleapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kawasdk.Utils.Common


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnStart)
        button.setOnClickListener {
            Common.USER_NAME = "rupesh"
            val intent = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)
        }

    }
}