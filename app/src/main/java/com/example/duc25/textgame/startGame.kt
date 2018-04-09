package com.example.duc25.runningman

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View
import android.widget.Button

@SuppressLint("ViewConstructor")
@Suppress("DEPRECATION")
/**
 * Created by duc25 on 3/10/2018.
 */
class startGame(context: Context,var screenW: Float,var screenH: Float): View(context) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.step1)

    override fun onDraw(canvas: Canvas){
        drawBitmap(canvas)
        drawLine(canvas)
        drawCopyRight(canvas)
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonPlayGame(): Button {
        val button = Button(context)
        button.text = "TAP TO PLAY"
        button.width = (screenW*0.33).toInt()
        button.height = (screenH*0.15).toInt()
        button.textSize = ((screenW*0.33).toInt())*0.03F
        button.x = (screenW*0.5F) - (screenW*0.33F)/2F
        button.y = screenH*0.55F - (screenH*0.15F)/2F
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(resources.getColor(R.color.colorOrange))
        return button
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonEasy(): Button {
        val button = Button(context)
        button.text = "Easy"
        button.width = (screenW*0.2).toInt()
        button.height = (screenH*0.01).toInt()
        button.textSize = ((screenW*0.33).toInt())*0.025F
        button.x = screenW*0.15F
        button.y = screenH*0.3F
        button.setTextColor(Color.BLACK)
        button.setBackgroundColor(resources.getColor(R.color.colorWhite))
        return button
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonMedium(): Button {
        val button = Button(context)
        button.text = "Medium"
        button.width = (screenW*0.2).toInt()
        button.height = (screenH*0.01).toInt()
        button.textSize = ((screenW*0.33).toInt())*0.025F
        button.x = (screenW*0.5F) - (screenW*0.2F)/2F
        button.y = screenH*0.3F
        button.setTextColor(Color.GREEN)
        button.setBackgroundColor(resources.getColor(R.color.colorWhite))
        return button
    }

    @SuppressLint("SetTextI18n")
    fun drawButtonHard(): Button {
        val button = Button(context)
        button.text = "Hard"
        button.width = (screenW*0.2).toInt()
        button.height = (screenH*0.01).toInt()
        button.textSize = ((screenW*0.33).toInt())*0.025F
        button.x = screenW*0.85F - (screenW*0.2F)
        button.y = screenH*0.3F
        button.setTextColor(Color.BLACK)
        button.setBackgroundColor(resources.getColor(R.color.colorWhite))
        return button
    }

    private fun drawBitmap(canvas: Canvas){
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, (screenW*0.07F).toInt(), (screenW*0.07F).toInt(), true)
                , screenW*0.5F-((screenW*0.07F)/2).toInt(), screenH*0.08F, paint)
    }

    private fun drawLine(canvas: Canvas){
        paint.setARGB(255, 77, 38, 0)
        paint.strokeWidth = screenW*0.002F
        canvas.drawLine(0F+screenW*0.2F,
                screenH*0.08F + (screenW*0.07F).toInt(),
                screenW-screenW*0.2F,
                screenH*0.08F + (screenW*0.07F).toInt(), paint)
    }

    private fun drawCopyRight(canvas: Canvas){
        paint.textSize = screenW*0.025F
        paint.setARGB(255, 77, 38, 0)
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("@IndieTeam", screenW*0.5F, screenH*0.9F, paint)
    }
}