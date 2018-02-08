package com.example.duc25.runningman

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_in_game.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


class InGameActivity : AppCompatActivity() {
    var Land: Land? = null
    var Man: Man? = null
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
        /*--GameUpDate*/
        GameUpdate().execute()
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
    inner class GameUpdate: AsyncTask<Void, String, Float>() {
        override fun doInBackground(vararg p0: Void?): Float {
            val timer = Timer()
            var XMan: Float?
            var XLand: Float?
            var i = 0
            timer.scheduleAtFixedRate(0, 1){
                XMan = Man!!.getXMan()
                XLand = Land!!.getXLand()
                if(XLand!! <= XMan!!){
                    //i++
                    //publishProgress("Game Over $i")
                }
            }
            return Land!!.getXLand()
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            Toast.makeText(this@InGameActivity, values[0].toString(),Toast.LENGTH_LONG).show()
        }

        override fun onPostExecute(result: Float?) {
            super.onPostExecute(result)
        }
    }

}
