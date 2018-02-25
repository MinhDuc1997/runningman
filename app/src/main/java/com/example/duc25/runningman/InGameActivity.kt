package com.example.duc25.runningman

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_in_game.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class InGameActivity : AppCompatActivity() {
    var Land: Land? = null
    var Man: Man? = null
    var textGame: textGame? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_in_game)
        val screenW:Float = getWidthHeigh("width")// never use
        val screenH:Float = getWidthHeigh("height")// never use
        /* --MAN--*/
        Man = Man(this, screenW, screenH)
        RelativeLayout.addView(Man)
        /* --LAND--*/
        Land = Land(this, screenW, screenH)
        RelativeLayout.addView(Land)
        /*--textGame*/
        textGame = textGame(this, screenW, screenH)
        RelativeLayout.addView(textGame)
        /*--GameUpDate*/
        GameUpdate(screenW).execute()
    }

    fun gameOver(Land: Land){
        val time = Timer()
        time.scheduleAtFixedRate(0, 5000){
            val posXland = Land.getXLand()
            Toast.makeText(this@InGameActivity,  posXland.toString(),Toast.LENGTH_LONG).show()
        }
    }

    fun getWidthHeigh(screen: String): Float{
        val display = windowManager.defaultDisplay
        val size = Point()
        var value: Int = 0
        display.getSize(size)
        if(screen == "width") {
            value = size.x
        }
        if(screen == "height"){
            value = size.y
        }
        //Toast.makeText(this@InGameActivity, value.toString(),Toast.LENGTH_LONG).show()
        return value.toFloat()
    }

    @SuppressLint("StaticFieldLeak")
    inner class GameUpdate(val screenW: Float): AsyncTask<Void, String, Float>() {
        val timer = Timer()
        var columOver = 0
        var game_Over = 0

        fun Score(){
            if(game_Over == 0) {
                if((Land!!.getXLand()!! + screenW * 0.02F) < Man!!.getXMan()) {
                    columOver++
                    if(columOver == 1) {
                        publishProgress("UpdateScore")
                    }
                }else {
                    columOver = 0
                }
            }
        }

        fun gameOver(){
            if(Land!!.getXLand() <= (Man!!.getXMan()+Man!!.getWidthMan()) && Land!!.getXLand()>Man!!.getXMan()  && (Man!!.getYMan()!!+Man!!.getHeightMan()) >= Land!!.getYLand()){
                //Land!!.valueAnimation.cancel()
                game_Over++
                if(game_Over == 1){
                    publishProgress("GameOver")
                }
            }
        }

        fun Update(){
            timer.scheduleAtFixedRate(0, 100){
                gameOver()
                //publishProgress("" + (Man!!.getYMan()+Man!!.getHeightMan()))
                Score()
            }
        }

        override fun doInBackground(vararg p0: Void?): Float {
            Update()
            return Land!!.getXLand()
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            if(values[0] == "UpdateScore"){
                textGame!!.updateScore()
            }
            if(values[0] == "GameOver"){
                Man!!.valueAnimation.cancel()
                Man!!.timer.cancel()
                Land!!.valueAnimation.cancel()
                val intent = Intent(this@InGameActivity, GameOverActivity::class.java)
                intent.putExtra("score", textGame!!.getScore().toString())
                startActivity(intent)
                finish()
            }
        }

        override fun onPostExecute(result: Float?) {
            super.onPostExecute(result)
        }
    }
}
