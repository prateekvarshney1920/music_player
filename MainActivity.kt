package com.example.musicplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()



    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        binding.seekbar.progress = 0
        binding.seekbar.max = mediaPlayer.duration

        binding.playBtn.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                binding.playBtn.setImageResource(R.drawable.baseline_pause_24)
            } else {
                mediaPlayer.pause()
                binding.playBtn.setImageResource(R.drawable.baseline_play_arrow_24)
            }
        }
        binding.seekbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, pos: Int, changed: Boolean) {
                if (changed){
                    mediaPlayer.seekTo(pos)

                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        runnable = Runnable {
            binding.seekbar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable,1000)
        }
        handler.postDelayed(runnable,1000)
        mediaPlayer.setOnCompletionListener {
            binding.playBtn.setImageResource(R.drawable.baseline_play_arrow_24)
            binding.seekbar.progress = 0

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
