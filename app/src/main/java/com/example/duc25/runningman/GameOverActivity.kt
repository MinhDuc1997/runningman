package com.example.duc25.runningman

import android.content.Intent
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game_over.*
import kotlinx.android.synthetic.main.activity_main.*

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_game_over)
        var score = intent.getStringExtra("score").toInt()
        val screenW = getWidthHeigh("width")
        val screenH = getWidthHeigh("height")
        val gameOver = gameOver(this, screenW, screenH)
        gameOver.score = score
        val button = gameOver.drawButton()
        relativeLayout.addView(button)
        relativeLayout.addView(gameOver)
        button.setOnClickListener(){
            val intent = Intent(this@GameOverActivity, InGameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getWidthHeigh(screen: String): Float{
        val display = windowManager.defaultDisplay
        val size = Point()
        var value: Int = 0
        display.getSize(size)
        if(screen == "width") {
            value = size.x
        }
        if(screen == "height"){
            value = size.y
        }
        //Toast.makeText(this@InGameActivity, value.toString(),Toast.LENGTH_LONG).show()
        return value.toFloat()
    }
}
