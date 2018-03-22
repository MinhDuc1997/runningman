package com.example.duc25.runningman


import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.AsyncTask
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import com.example.duc25.audio.audioGame
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

@SuppressLint("ViewConstructor")
class Man(contex: Context, var screenW: Float, var screenH: Float): View(contex){
    var bitmap = BitmapFactory.decodeResource(resources, R.drawable.step1)
    private var PosX: Float = screenW*0F
    private var PosY: Float = screenH*0.74F
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)//pass param khử răng cưa
    val valueAnimation = ValueAnimator.ofFloat(PosY, screenH*0.25F)
    val valueAnimation1 = ValueAnimator.ofFloat(  0.12F, 0F)
    val timer = Timer()
    private var run = 1
    var jum = 1 //when gameover set jump = 0 don't start valueAnimation after cancel
    var moveR = 1
    var moveRok = 0
    var Audio = audioGame(contex)
    var time: Long = 0

    //control jump
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean{
        if(event.action == MotionEvent.ACTION_DOWN){
            if(getYMan() >= (screenH*0.74-10F) && moveRok == 1) {
                if(jum == 1) {
                    valueAnimation.cancel()
                    moveMan()
                    Audio.audioJump()
                }
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas){
        drawMan(canvas)
        if(moveR == 1) {
            moveRight()
        }
    }

    private fun drawMan(canvas: Canvas){
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, (screenW*0.06).toInt(), (screenW*0.06).toInt(), true)
                , PosX, PosY, paint)
    }

    private fun moveRight(){
        moveR = 0
        valueAnimation1.addUpdateListener {
            val value = it.animatedValue as Float
            if(value>0){
                PosX = screenW*(0.12F-value)
                postInvalidateOnAnimation()
            }else{
                moveRok = 1
                valueAnimation1.cancel()
                if(run == 1){
                    run = 0
                    ManRun().execute()
                }
            }
        }

        valueAnimation1.duration = 350
        valueAnimation1.interpolator = LinearInterpolator()
        valueAnimation1.start()
    }

    private fun moveMan(){
        valueAnimation.addUpdateListener {
            val value = it.animatedValue as Float
            if(value>0){
                PosY = value
            }else{
                valueAnimation.cancel()
            }
            postInvalidateOnAnimation()
        }
        valueAnimation.repeatMode = ValueAnimator.REVERSE
        valueAnimation.repeatCount = 1
        valueAnimation.duration = time
        valueAnimation.interpolator = AccelerateInterpolator(0.7f) // tang toc
        //valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.start()
    }

    fun getYMan(): Float{
        return PosY
    }

    fun getXMan(): Float{
        return PosX
    }

    fun getWidthMan(): Float{
        return screenW*0.06F
    }

    fun getHeightMan(): Float{
        return screenW*0.06F
    }


    @SuppressLint("StaticFieldLeak")
    inner class ManRun: AsyncTask<Void, String, Float>() {
        override fun doInBackground(vararg p0: Void?): Float {
            var i = 0
            timer.scheduleAtFixedRate(0, 100){
                if(getYMan() == (screenH*0.74F)) {
                    if (i == 0) {
                        i = 1
                        publishProgress("1")
                    } else {
                        i = 0
                        publishProgress("0")
                    }
                }
            }
            return getYMan()
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            if(values[0]!!.toInt() == 1){
                bitmap = BitmapFactory.decodeResource(resources, R.drawable.step2)
            }
            if(values[0]!!.toInt() == 0){
                bitmap = BitmapFactory.decodeResource(resources, R.drawable.step1)

            }
            postInvalidateOnAnimation()
        }

    }
}