package com.example.duc25.runningman

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.RelativeLayout


/**
 * Created by duc25 on 3/8/2018.
 */
class background {
    fun backgroundLand(context: Context, rl: RelativeLayout) {
        val objectAnimation = ObjectAnimator.ofObject(rl,
                "backgroundColor",
                ArgbEvaluator(),
                ContextCompat.getColor(context, R.color.colorORange_2),
                ContextCompat.getColor(context, R.color.colorOrange),
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorMe),
                ContextCompat.getColor(context, R.color.colorGreen1))

        objectAnimation.repeatMode = ValueAnimator.REVERSE
        objectAnimation.repeatCount = 1000000
        objectAnimation.duration = 70000
        objectAnimation.start()
    }

}