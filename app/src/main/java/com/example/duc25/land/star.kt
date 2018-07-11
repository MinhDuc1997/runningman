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
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
    private var starX = XX
    val valueAnimation = ValueAnimator.ofFloat(starX, 0F)
    private var check = 1
    private val random = Random()
    private var rand = random(0F, 150F)
    private var rand1 = random(0F, 50F)

    override fun onDraw(canvas: Canvas){
        drawStar(canvas)
        if(check == 1) {
            moveStar()
        }
    }

    private fun random(from: Float, to: Float): Float{
        return random.nextInt(to.toInt() - from.toInt()) + from
    }

    private fun drawStar(canvas: Canvas){
        paint.setARGB(255, 254, 255, 253)
        paint.strokeWidth = screenW*0.003F//set độ dày
        for(i in (screenH*0.32F).toInt() downTo 0 ) {
            if(i%200 == 0){
                //canvas.drawLine(i.toFloat(), rainY, i.toFloat(), rainY+(screenH*0.015F), paint)
                canvas.drawPoint(starX + (screenW * (rand1 * 0.001F)), i.toFloat() + (screenW * (rand * 0.0007F)), paint)
            }
        }
    }

    private fun moveStar(){
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
        valueAnimation.duration = (XX/0.035).toLong()
        valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.start()
    }
}