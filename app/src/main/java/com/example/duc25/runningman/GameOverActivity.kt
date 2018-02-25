package com.example.duc25.runningman

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_game_over)
        var score = intent.getStringExtra("score")
        textView3.text = "Score: $score"
        button2.setOnClickListener(){
            val intent = Intent(this@GameOverActivity, InGameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
