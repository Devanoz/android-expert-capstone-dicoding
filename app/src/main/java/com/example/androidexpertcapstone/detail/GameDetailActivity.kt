package com.example.androidexpertcapstone.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.androidexpertcapstone.core.data.Resource
import com.example.androidexpertcapstone.core.domain.model.GameDetail
import com.example.androidexpertcapstone.core.ui.GamesAdapter
import com.example.androidexpertcapstone.databinding.ActivityGameDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {
    private val viewModels: GameDetailViewModel by viewModels()
    private lateinit var binding: ActivityGameDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = intent.getIntExtra(GamesAdapter.KEY_GAME_ID,0)

        viewModels.getGameById(gameId).observe(this){
            when(it) {
                is Resource.Error -> {
                    Toast.makeText(this, "Failed to get data",Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Log.d("gameDetailStatus","loading")
                }
                is Resource.Success -> {
                    it.data?.let { gameDetail -> setGameDetail(gameDetail) }
                }
            }
        }
    }

    private fun setGameDetail(game: GameDetail) {
        Glide.with(this).load(game.backgroundImage).into(binding.imvGameBackground)
        binding.tvGameName.text = game.name
    }
}