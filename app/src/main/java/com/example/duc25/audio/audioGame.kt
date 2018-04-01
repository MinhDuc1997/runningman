package com.example.duc25.audio

import android.content.Context
import android.media.AudioManager.STREAM_MUSIC
import android.media.MediaPlayer
import android.media.SoundPool
import com.example.duc25.runningman.R
import java.util.*

@Suppress("DEPRECATION")
/**
 * Created by duc25 on 3/8/2018.
 */
class audioGame(var context: Context){
    var bgAudio = MediaPlayer.create(context, R.raw.knock_knock)
    val audioSP = SoundPool(2, STREAM_MUSIC, 0)
    private val audioJid = audioSP.load(context, R.raw.bubble, 1)
    private val audioGoverId = audioSP.load(context, R.raw.game_over, 1)
    private val random = Random()

    fun backgroundAudio(){
        when(rand(1,4)){
            1 -> bgAudio = MediaPlayer.create(context, R.raw.knock_knock)
            2 -> bgAudio = MediaPlayer.create(context, R.raw.likey)
            3 -> bgAudio = MediaPlayer.create(context, R.raw.heroine)
            4 -> bgAudio = MediaPlayer.create(context, R.raw.fantasy_game_background)
        }
        bgAudio.start()
        bgAudio.isLooping = true
    }

    fun audioJump(){
        audioSP.play(audioJid, 1F, 0.5F, 1, 0, 1F)
    }

    fun audioGameOver(){
        audioSP.play(audioGoverId, 1F, 1F, 2, 0, 1.1F)
    }

    private fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }
}