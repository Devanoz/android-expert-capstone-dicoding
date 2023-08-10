package com.example.androidexpertcapstone.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.androidexpertcapstone.R
import com.example.androidexpertcapstone.databinding.ActivityGameDetailBinding
import com.example.core.data.Resource
import com.example.core.domain.model.GameDetail
import com.example.core.ui.GamesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {
    private val viewModels: GameDetailViewModel by viewModels()
    private lateinit var binding: ActivityGameDetailBinding

    private var gameId: Int = 0
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameId = intent.getIntExtra(GamesAdapter.KEY_GAME_ID,0)
        isFavorite = intent.getBooleanExtra(GamesAdapter.KEY_FAVOURITE,false)

        setFabFavourite(isFavorite)

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

                else -> {}
            }
        }

        binding.fabFavourite.setOnClickListener {
            isFavorite = !isFavorite
            viewModels.setFavourite(gameId,isFavorite)
            setFabFavourite(isFavorite)
        }
    }

    private fun setGameDetail(game: GameDetail) {
        Glide.with(this).load(game.backgroundImage).into(binding.imvGameBackground)
        binding.tvGameName.text = game.name
    }

    private fun setFabFavourite(isFavorite: Boolean) {
        if(isFavorite) {
            binding.fabFavourite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_faforite_enabled))
        }else {
            binding.fabFavourite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_disabled))
        }
    }
}