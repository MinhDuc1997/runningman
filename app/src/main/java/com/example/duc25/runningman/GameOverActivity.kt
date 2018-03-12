package com.example.duc25.runningman

import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_game_over.*
import kotlinx.android.synthetic.main.activity_in_game.*

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_game_over)

        val score = intent.getStringExtra("score").toInt()
        val level = intent.getStringExtra("level")
        val screenW = getWidthHeigh("width")
        val screenH = getWidthHeigh("height")
        val gameOver = gameOver(this, screenW, screenH)
        gameOver.score = score
        val buttonPlayAgain = gameOver.drawButtonPlayAgain()
        val buttonMenu = gameOver.drawButtonMenu()
        GameOver.addView(buttonPlayAgain)
        GameOver.addView(buttonMenu)
        GameOver.addView(gameOver)
        buttonMenu.setOnClickListener(){
            val intent = Intent(this@GameOverActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonPlayAgain.setOnClickListener(){
            val intent = Intent(this@GameOverActivity, InGameActivity::class.java)
            intent.putExtra("level", "$level")
            startActivity(intent)
            finish()
        }
    }

    private fun getWidthHeigh(screen: String): Float{
        val display = windowManager.defaultDisplay
        val size = Point()
        var value = 0
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
