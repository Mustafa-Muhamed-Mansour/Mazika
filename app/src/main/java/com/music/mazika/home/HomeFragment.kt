package com.music.mazika.home

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.music.mazika.R
import com.music.mazika.adapter.MazikaAdapter
import com.music.mazika.databinding.FragmentHomeBinding
import com.music.mazika.interfaces.ClickedOfSong
import com.music.mazika.model.SongModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(), ClickedOfSong {


    private lateinit var binding: FragmentHomeBinding


    val viewModel: HomeViewModel by viewModels()

    private lateinit var mazikaAdapter: MazikaAdapter

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initAdapter()
        fetchSongs()

    }

    private fun initAdapter() {
        mazikaAdapter = MazikaAdapter(this)
    }

    private fun fetchSongs() {
        viewModel
            .getAllSongs()
            .observe(viewLifecycleOwner) {
                mazikaAdapter.differ.submitList(it.toList())
                binding.rVMazika.apply {
                    this.adapter = mazikaAdapter
                    this.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    this.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
    }

//    private fun clickedView() {
//        binding.imgStop.setOnClickListener {
//            mediaPlayer!!.stop()
//            binding.imgPlay.setImageResource(R.drawable.play)
//        }
//    }

    override fun checkedOfSongToPlay(animation: LottieAnimationView) {
        animation.pauseAnimation()
        Toast.makeText(requireContext(), R.string.select_the_song, Toast.LENGTH_SHORT).show()
    }

    override fun clickedOfSongToPlayAndPause(songModel: SongModel, animation: LottieAnimationView) {
        mediaPlayer = MediaPlayer.create(requireContext(), songModel.songUrl.toUri())
        mediaPlayer!!.start()
        binding.imgPlay.setImageResource(R.drawable.pause)
        animation.playAnimation()
        binding.imgPlay.setOnClickListener {
            if (mediaPlayer!!.isPlaying) {
                animation.pauseAnimation()
                mediaPlayer!!.pause()
                binding.imgPlay.setImageResource(R.drawable.play)
            } else {
                mediaPlayer!!.start()
                binding.imgPlay.setImageResource(R.drawable.pause)
                animation.playAnimation()
            }
        }
    }
}