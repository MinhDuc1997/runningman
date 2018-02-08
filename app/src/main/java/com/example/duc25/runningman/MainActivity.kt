package com.example.duc25.runningman

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        button.setOnClickListener(){
            //Toast.makeText(this@MainActivity,"ok",Toast.LENGTH_LONG).show()
            val intent = Intent(this@MainActivity,InGameActivity::class.java)
            startActivity(intent)
        }
    }
}
