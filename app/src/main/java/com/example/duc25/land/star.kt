package com.example.duc25.runningman

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*

/**
 * Created by duc25 on 3/7/2018.
 */
class Star(context: Context, var screenW: Float, var screenH: Float, var XX: Float): View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
    var starX = XX
    val valueAnimation = ValueAnimator.ofFloat(starX, 0F)
    var check = 1
    val random = Random()
    var rand = random(0F, 150F)
    var rand1 = random(0F, 50F)

    override fun onDraw(canvas: Canvas){
        drawStar(canvas)
        if(check == 1) {
            moveStar()
        }
    }

    fun random(from: Float, to: Float): Float{
        return random.nextInt(to.toInt() - from.toInt()) + from
    }

    fun drawStar(canvas: Canvas){
        paint.setARGB(255, 254, 255, 253)
        paint.setStrokeWidth(screenW*0.003F)//set độ dày
        for(i in (screenH*0.32F).toInt() downTo 0 ) {
            if(i%200 == 0){
                //canvas.drawLine(i.toFloat(), rainY, i.toFloat(), rainY+(screenH*0.015F), paint)
                canvas.drawPoint(starX + (screenW * (rand1 * 0.005F)), i.toFloat() + (screenW * (rand * 0.0007F)), paint)
            }
        }
    }

    fun moveStar(){
        check = 0
        valueAnimation.addUpdateListener {
            val value = it.animatedValue as Float
            if(value > 0F) {
                starX = value
                postInvalidateOnAnimation()
            }
            else{
                check = 1
                starX = screenW
                valueAnimation.cancel()
                postInvalidateOnAnimation()
            }
        }
        valueAnimation.duration = (XX/0.05).toLong()
        valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.start()
    }
}