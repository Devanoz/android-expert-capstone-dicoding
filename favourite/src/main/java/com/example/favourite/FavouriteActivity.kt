package com.example.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexpertcapstone.di.FavouriteModuleDependencies
import com.example.core.ui.FavouriteGamesAdapter
import com.example.favourite.databinding.ActivityFavouriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavouriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouriteBinding

    @Inject
    lateinit var factory: FavouriteViewModelFactory

    private val viewModel: FavouriteViewModel by viewModels { factory }

    private lateinit var rvFavourite: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavouriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBarFavourite)

        rvFavourite = binding.rvFavourite
        rvFavourite.layoutManager = LinearLayoutManager(this)

        binding.emptyAnimation.visibility = View.INVISIBLE
        viewModel.favouriteGames.observe(this) {
            Log.d("FavouriteActivityXX",it.toString())
            if(it.isEmpty()){
                binding.emptyAnimation.visibility = View.VISIBLE
                rvFavourite.adapter = FavouriteGamesAdapter(it) { _, _ -> }
            }else {
                binding.emptyAnimation.visibility = View.INVISIBLE
                rvFavourite.adapter = FavouriteGamesAdapter(it) { gameId,isFavourite ->
                    viewModel.setFavourite(gameId,isFavourite)
                }
            }

        }

    }
}