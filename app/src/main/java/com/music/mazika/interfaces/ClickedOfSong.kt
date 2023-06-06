package com.music.mazika.interfaces

import com.airbnb.lottie.LottieAnimationView
import com.music.mazika.model.SongModel

interface ClickedOfSong {

    fun checkedOfSongToPlay(animation: LottieAnimationView)
    fun clickedOfSongToPlayAndPause(songModel: SongModel, animation: LottieAnimationView)
}