package com.example.duc25.runningman

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import java.util.*

/**
 * Created by duc25 on 1/26/2018.
 */
class Land(context: Context, var screenW: Float, var screenH: Float): View(context){
    var random = Random()
    var near1: Float = (screenW/100)*60F
    var near2: Float = (screenW/100)*40F
    var near3: Float = screenW
    var rand1 = random(20F, 30F)
    var rand2 = random(20F, 30F)
    var rand3 = random(20F, 35F)
    val valueAnimation = ValueAnimator.ofFloat(near3, 0F)//giam tu trai qua phai
    var check = 1

    override fun onDraw(canvas: Canvas){
        drawLand(canvas)
        //drawCollums(canvas, rand1, near1)
        //drawCollums(canvas, rand2, near2)
        drawCollums(canvas, rand3, near3) //hight // near
        if(check == 1){
            moveLand()
        }
    }

    fun random(from: Float, to: Float): Float{
        return random.nextInt(to.toInt() - from.toInt()) + from
    }

    fun moveLand(){
        check = 0
        valueAnimation.addUpdateListener {
            val value = it.animatedValue as Float
            near3 = value
            if(near3 >0F) {
                postInvalidateOnAnimation()
            }else{
                check = 1
                rand3 = random(20F, 35F)
                near3 = screenW
                postInvalidateOnAnimation()
            }
        }
        //valueAnimation.repeatMode = ValueAnimator.REVERSE
        //valueAnimation.repeatCount = 1
        valueAnimation.duration = 2500
        valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.start()
    }

    fun getYLand(){

    }

    fun getXLand(): Float{
        return near3
    }

    fun updateLand(){

    }

    fun drawCollums(canvas: Canvas, hight: Float, near: Float){
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
        paint.setARGB(255, 77, 38, 0)
        paint.setStrokeWidth((screenW /100)*3)
        canvas.drawLine(near, ((screenH/100)*100) - ((screenH/100)*10), near, ((screenH/100)*100) - ((screenH/100)*hight), paint)
    }

    fun drawLand(canvas: Canvas){
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
        paint.setARGB(255, 77, 38, 0)
        paint.setStrokeWidth(((screenH/100)*100) - ((screenH/100)*70))
        canvas.drawLine(0F, height.toFloat(), width.toFloat(), height.toFloat(), paint)
    }
}