package com.example.duc25.runningman

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Point
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.example.duc25.audio.audioGame
import kotlinx.android.synthetic.main.activity_in_game.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate

class InGameActivity : AppCompatActivity() {
    protected var Land: Land? = null
    protected var Man: Man? = null
    protected var Star = arrayOfNulls<Star>(size = 100)
    protected var textGame: textGame? = null
    protected var game_Over = 0
    protected var Audio: audioGame? = null
    private var level = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_in_game)

        val screenW:Float = getWidthHeigh("width")
        val screenH:Float = getWidthHeigh("height")
        level = intent.getStringExtra("level")


        /*--Background--*/
        val Background = background()
        Background.backgroundLand(this, GamePlay)
        /*--Audio--*/
        Audio = audioGame(this)
        Audio!!.backgroundAudio()
        /*--Star--*/
        //var j = 0F
//        for(i in 0 until 30){
//            Star[i] = Star(this, screenW, screenH, screenW + (screenW*j))
//            GamePlay.addView(Star[i])
//            j += 0.02F
//        }
        /* --Man--*/
        Man = Man(this, screenW, screenH)
        when(level){
            "easy" -> Man!!.time = (screenH*0.3F/((screenH*0.3F)*0.0055F)).toLong()
            "medium" -> Man!!.time = (screenH*0.3F/((screenH*0.3F)*0.0063F)).toLong()
            "hard" -> Man!!.time = (screenH*0.3F/((screenH*0.3F)*0.0077F)).toLong()
            else -> Man!!.time = (screenH*0.3F/((screenH*0.3F)*0.006F)).toLong()
        }
        GamePlay.addView(Man)
        /* --Land--*/
        Land = Land(this, screenW, screenH)
        when(level){
            "easy" -> Land!!.time = (screenW/(screenW*0.0007F)).toLong()
            "medium" -> Land!!.time = (screenW/(screenW*0.00088F)).toLong()
            "hard" -> Land!!.time = (screenW/(screenW*0.0012F)).toLong()
            else -> Land!!.time = (screenW/(screenW*0.00088F)).toLong()
        }
        GamePlay.addView(Land)
        /*--textGame*/
        textGame = textGame(this, screenW, screenH)
        GamePlay.addView(textGame)
        /*--GameUpDate*/
        GameUpdate(screenW).execute()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun getWidthHeigh(screen: String): Float{
        val display = windowManager.defaultDisplay
        val size = Point()
        var value = 0
        display.getSize(size)
        if(screen == "width") {
            value = size.x
        }
        if(screen == "height"){
            value = size.y
        }
        return value.toFloat()
    }

    @SuppressLint("StaticFieldLeak")
    inner class GameUpdate(val screenW: Float): AsyncTask<Void, String, Float>() {
        private val timer = Timer()
        private var columOver = 0

        private fun Score(){
            if(game_Over == 0) {
                if((Land!!.getXCollum(1) + screenW * 0.015F) < Man!!.getXMan() || (Land!!.getXCollum(0) + screenW * 0.015F) < Man!!.getXMan()) {
                    columOver++
                    if(columOver == 1) {
                        publishProgress("UpdateScore")
                    }
                }else {
                    columOver = 0
                }
            }
        }

        private fun gameOver(){
            if(Land!!.getXCollum(1) <= (Man!!.getXMan()+Man!!.getWidthMan()) && Land!!.getXCollum(1)>=Man!!.getXMan()  && (Man!!.getYMan() +Man!!.getHeightMan()) >= Land!!.getYCollum(1) ||
                    Land!!.getXCollum(0) <= (Man!!.getXMan()+Man!!.getWidthMan()) && Land!!.getXCollum(0)>=Man!!.getXMan()  && (Man!!.getYMan() +Man!!.getHeightMan()) >= Land!!.getYCollum(0)){
                game_Over++
                if(game_Over == 1){
                    publishProgress("GameOver")
                }
            }
        }

        private fun Update(){
            timer.scheduleAtFixedRate(0, 20){
                gameOver()
                Score()
            }
        }

        override fun doInBackground(vararg p0: Void?): Float {
            Update()
            return Land!!.getXCollum(1)
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            if(values[0] == "UpdateScore"){
                textGame!!.updateScore()
            }
            if(values[0] == "GameOver"){
                Man!!.jum = 0
                Man!!.valueAnimation.cancel()
                Man!!.timer.cancel()
                Man!!.Audio.audioSP.stop(0)
                Man!!.Audio.audioSP.release()
                Land!!.valueAnimation.cancel()
                Land!!.valueAnimation1.cancel()
//                for(i in 0 until 30){
//                    Star[i]!!.valueAnimation.cancel()
//                }
                Audio!!.audioGameOver()
                Audio!!.bgAudio.stop()
                Audio!!.bgAudio.release()
                timer.schedule(3000){
                    Audio!!.audioSP.stop(1)
                    Audio!!.audioSP.release()
                }
                val intent = Intent(this@InGameActivity, GameOverActivity::class.java)
                intent.putExtra("score", textGame!!.getScore().toString())
                intent.putExtra("level", level)
                finish()
                startActivity(intent)
            }
        }

    }
}
