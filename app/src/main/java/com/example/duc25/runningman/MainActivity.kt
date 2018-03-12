package com.example.duc25.runningman

import android.content.Intent
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val screenW:Float = getWidthHeigh("width")
        val screenH:Float = getWidthHeigh("height")

        val startGame = startGame(this, screenW, screenH)
        val buttonPlayGame = startGame.drawButtonPlayGame()
        val buttonEasy = startGame.drawButtonEasy()
        val buttonMedium = startGame.drawButtonMedium()
        val buttonHard = startGame.drawButtonHard()
        start.addView(buttonPlayGame)
        start.addView(buttonEasy)
        start.addView(buttonMedium)
        start.addView(buttonHard)
        start.addView(startGame)

        val intent = Intent(this@MainActivity,InGameActivity::class.java)
        var level = ""

        buttonEasy.setOnClickListener(){
            buttonEasy.setTextColor(Color.GREEN)
            buttonMedium.setTextColor(Color.BLACK)
            buttonHard.setTextColor(Color.BLACK)
            level = "easy"
        }

        buttonMedium.setOnClickListener(){
            buttonEasy.setTextColor(Color.BLACK)
            buttonMedium.setTextColor(Color.GREEN)
            buttonHard.setTextColor(Color.BLACK)
            level = "medium"
        }

        buttonHard.setOnClickListener(){
            buttonEasy.setTextColor(Color.BLACK)
            buttonMedium.setTextColor(Color.BLACK)
            buttonHard.setTextColor(Color.GREEN)
            level = "hard"
        }

        buttonPlayGame.setOnClickListener(){
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
