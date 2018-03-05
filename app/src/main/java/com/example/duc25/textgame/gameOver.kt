package com.example.duc25.runningman

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import com.example.duc25.database.scoreDB

/**
 * Created by duc25 on 3/4/2018.
 */
class gameOver(context: Context, var screenW: Float, var screenH: Float): View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var score: Int? = null
    val scoredb = scoreDB(context)
    val name_device:String = android.os.Build.MODEL
    override fun onDraw(canans: Canvas){
        drawGameOver(canans)
    }

    fun insertScore(){
        scoredb.insertScore(name_device, score)
    }

    fun readScore(): Int{
        return scoredb.readScore()
    }

    fun updateScore(){
        scoredb.updateScore(name_device, score)
    }

    fun drawGameOver(canans: Canvas){
        insertScore()
        if(score!! > readScore()){
            updateScore()
        }
        paint.textSize = screenW*0.05F
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textAlign = Paint.Align.CENTER
        paint.setARGB(255, 255, 156, 7)
        canans.drawText("Game Over", screenW*0.5F, screenH*0.35F, paint)
        paint.textSize = screenW*0.026F
        paint.setARGB(255, 8, 175, 43)
        canans.drawText("Score: $score  Max: " + readScore(), screenW*0.5F, screenH*0.425F, paint)
    }

    fun drawButton(): Button{
        val button = Button(context)
        button.text = "TAP TO AGAIN"
        button.textSize = screenW*0.01F
        button.width = (screenW*0.33).toInt()
        button.height = (screenH*0.15).toInt()
        button.x = (screenW*0.5F) - (screenW*0.35F)/2F
        button.y = screenH*0.47F
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(resources.getColor(R.color.colorOrange))
        return button
    }
}