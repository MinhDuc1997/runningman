package com.example.duc25.runningman

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import com.example.duc25.database.scoreDB

@Suppress("DEPRECATION")
@SuppressLint("ViewConstructor")
/**
 * Created by duc25 on 3/4/2018.
 */
class gameOver(context: Context, var screenW: Float, var screenH: Float): View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var score: Int? = null
    private val scoredb = scoreDB(context)
    private val name_device:String = android.os.Build.MODEL

    override fun onDraw(canans: Canvas){
        drawGameOver(canans)
    }

    private fun insertScore(){
        scoredb.insertScore(name_device, score)
    }

    private fun readScore(): Int{
        return scoredb.readScore()
    }

    private fun updateScore(){
        scoredb.updateScore(name_device, score)
    }

    private fun drawGameOver(canans: Canvas){
        insertScore()
        if(score!! > readScore()){
            updateScore()
        }
        paint.textSize = screenW*0.05F
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textAlign = Paint.Align.CENTER
        paint.setARGB(255, 255, 156, 7)
        canans.drawText("Game Over", screenW*0.5F, screenH*0.25F, paint)
        paint.textSize = screenW*0.026F
        paint.setARGB(255, 8, 175, 43)
        canans.drawText("Score: $score  Max: " + readScore(), screenW*0.5F, screenH*0.325F, paint)
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonPlayAgain(): Button{
        val button = Button(context)
        button.text = "TAP TO AGAIN"
        button.width = (screenW*0.33).toInt()
        button.height = (screenH*0.15).toInt()
        button.textSize = ((screenW*0.33).toInt())*0.03F
        button.x = (screenW*0.5F) - (screenW*0.33F)/2F
        button.y = screenH*0.37F
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(resources.getColor(R.color.colorOrange))
        return button
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonMenu(): Button{
        val button = Button(context)
        button.text = "Menu"
        button.width = (screenW*0.22).toInt()
        button.height = (screenH*0.05).toInt()
        button.textSize = ((screenW*0.22).toInt())*0.03F
        button.x = (screenW*0.5F) - (screenW*0.22F)/2F
        button.y = screenH*0.55F
        button.setTextColor(Color.BLACK)
        button.setBackgroundColor(resources.getColor(R.color.colorWhite))
        return button
    }
}