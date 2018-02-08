package com.example.duc25.runningman


import android.animation.ValueAnimator
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast


class Man(contex: Context, var screenW: Float, var screenH: Float): View(contex){
    var bitmap = BitmapFactory.decodeResource(resources, R.drawable.step1)
    var x1:Float = (screenW/100)*10
    var y1:Float = (screenH/100)*70
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
    val valueAnimation = ValueAnimator.ofFloat(y1, (screenH/100)*35)

    //control jump
    override fun onTouchEvent(event: MotionEvent): Boolean{
        if(event.action === MotionEvent.ACTION_DOWN){
            if(getYMan() == ((screenH/100)*70)) {
                moveMan()
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas){
        drawEmemple(canvas)
        drawMan(canvas)
    }

    fun moveMan(){
        valueAnimation.addUpdateListener {
            val value = it.animatedValue as Float
            y1 = value
            postInvalidateOnAnimation()
        }
        valueAnimation.repeatMode = ValueAnimator.REVERSE
        valueAnimation.repeatCount = 1
        valueAnimation.duration = 400
        //valueAnimation.interpolator = AccelerateInterpolator(1.5f) // tang toc
        valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.start()
    }

    fun getYMan(): Float{
        return y1
    }

    fun getXMan(): Float{
        return x1
    }

    fun drawMan(canvas: Canvas){
        canvas.drawBitmap(bitmap, x1, y1, paint)
    }

    fun drawEmemple(canvas: Canvas){
        canvas.drawRGB(255, 219, 77)//BG color
        paint.setARGB(255, 255, 0, 0)
        //paint.setStrokeWidth(2f)//set độ dày
        //canvas.drawLine(0f, 0f, width.toFloat(), height.toFloat(), paint)
        //paint.setStrokeWidth(10f)
        //canvas.drawLine(0f, 60f, width.toFloat(), 60f, paint)
        //paint.setStrokeWidth(15f)//set độ dày
        //canvas.drawPoint(screenW/2, screenH/2, paint)
    }

}