package com.example.duc25.audio

import android.content.Context
import android.media.AudioManager.STREAM_MUSIC
import android.media.MediaPlayer
import android.media.SoundPool
import com.example.duc25.runningman.R

/**
 * Created by duc25 on 3/8/2018.
 */
class audioGame(var context: Context){
    var bgAudio = MediaPlayer.create(context, R.raw.fantasy_game_background)
    val audioSP = SoundPool(1, STREAM_MUSIC, 0)
    val audioJid = audioSP.load(context, R.raw.bubble, 1)
    val audioGoverId = audioSP.load(context, R.raw.game_over, 1)

    fun backgroundAudio(){
        bgAudio = MediaPlayer.create(context, R.raw.fantasy_game_background)
        bgAudio.start()
        bgAudio.isLooping = true
    }

    fun audioJump(){
        audioSP.play(audioJid, 1F, 1F, 0, 0, 1F)
    }

    fun audioGameOver(){
        audioSP.play(audioGoverId, 1F, 1F, 0, 0, 1F)
    }
}