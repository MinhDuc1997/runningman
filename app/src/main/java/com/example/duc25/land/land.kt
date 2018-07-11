package com.example.duc25.runningman

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.view.animation.LinearInterpolator
import java.util.*

/**
 * Created by duc25 on 1/26/2018.
 */
class Land(context: Context, var screenW: Float, var screenH: Float): View(context){
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
    private var random = Random()
    protected var nearArray = arrayOf<Float>(screenW, screenW)
    protected var randArray = arrayOf<Float>(random(65F, 80F), random(65F, 80F))
    val valueAnimation = ValueAnimator.ofFloat(nearArray[0], 0F)//giam tu trai qua phai
    val valueAnimation1 = ValueAnimator.ofFloat(nearArray[1], 0F)
    private var checkMove = arrayOf<Int>(1, 1)
    private var makeCol = 0
    var time: Long = 0

    override fun onDraw(canvas: Canvas){
        drawLand(canvas)
        drawCollums(canvas, randArray[0], nearArray[0]) //hight // near
        if(checkMove[0] == 1){
            moveCollum(1)
        }
        if(nearArray[0] < random(screenW*0.485F, screenW*0.5F)) {
            makeCol = 1
        }
        if(makeCol == 1){
            drawCollums(canvas, randArray[1], nearArray[1])
            if(checkMove[1] == 1) {
                moveCollum(0)
            }
        }
    }

    private fun random(from: Float, to: Float): Float{
        return random.nextInt(to.toInt() - from.toInt()) + from
    }

    private fun drawLand(canvas: Canvas){
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
        paint.setARGB(255, 77, 38, 0)
        paint.strokeWidth = screenW*2
        canvas.drawLine(0F, (screenH*0.72F + screenW*0.07F), 0F, screenH, paint)
    }

    private fun drawCollums(canvas: Canvas, hight: Float, near: Float){
        paint.setARGB(255, 77, 38, 0)
        paint.strokeWidth = screenW*0.015F
        canvas.drawLine(near, (screenH*0.72F + screenW*0.07F), near, ((screenH/100)*hight), paint)
    }

    private fun moveCollum(Col: Int){
        if(Col == 1) {
            checkMove[0] = 0
            valueAnimation.addUpdateListener {
                val value = it.animatedValue as Float
                if (value > 0F) {
                    nearArray[0] = value
                } else {
                    checkMove[0] = 1
                    randArray[0] = random(60F, 80F)
                    nearArray[0] = screenW
                    valueAnimation.cancel()
                }
                postInvalidateOnAnimation()
            }
            valueAnimation.duration = time
            valueAnimation.interpolator = LinearInterpolator()
            valueAnimation.start()
        }else{
            checkMove[1] = 0
            valueAnimation1.addUpdateListener {
                val value = it.animatedValue as Float
                if (value > 0F) {
                    nearArray[1] = value
                } else {
                    checkMove[1] = 1
                    randArray[1] = random(60F, 80F)
                    nearArray[1] = screenW
                    valueAnimation1.cancel()
                }
                postInvalidateOnAnimation()
            }
            valueAnimation1.duration = time
            valueAnimation1.interpolator = LinearInterpolator()
            valueAnimation1.start()
        }
    }

    fun getYCollum(num: Int): Float{
        val y: Float
        if(num == 1) {
            y = (screenH / 100) * randArray[0]
        }else{
            y = (screenH / 100) * randArray[1]
        }
        return y
    }

    fun getXCollum(num: Int): Float{
        val x: Float
        if(num == 1) {
            x = nearArray[0]
        }else{
            x = nearArray[1]
        }
        return x
    }

}