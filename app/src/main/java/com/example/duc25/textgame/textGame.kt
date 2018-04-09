package com.example.duc25.runningman

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface.*
import android.view.View

/**
 * Created by duc25 on 2/18/2018.
 */
class textGame(context: Context, var screenW: Float, var screenH: Float): View(context){
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var scoreGame: Int = 0

    override fun onDraw(canvas: Canvas){
        drawScore(canvas)
    }

    private fun drawScore(canvas: Canvas){
        paint.textSize = screenW*0.025F
        paint.setARGB(255, 77, 38, 0)
        paint.typeface = DEFAULT_BOLD
        canvas.drawText("Score: " + scoreGame, screenW*0.85F, screenH*0.1F, paint)
    }

    private fun setScore(){
        scoreGame += 1
    }

    fun getScore(): Int{
        return scoreGame
    }

    fun updateScore(){
        setScore()
        postInvalidateOnAnimation()
    }

}