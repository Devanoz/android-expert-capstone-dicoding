package com.example.androidexpertcapstone

import android.graphics.drawable.GradientDrawable.Orientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexpertcapstone.core.data.Resource
import com.example.androidexpertcapstone.core.ui.GamesAdapter
import com.example.androidexpertcapstone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvGames: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvGames = binding.rvGames
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.games.observe(this) { resources ->
            Log.d("gameList", resources::class.java.simpleName)
            when (resources) {
                is Resource.Error -> {
                    Toast.makeText(this, "api error", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Log.d("resourceStatus", "games is loading")
                }

                is Resource.Success -> {
                    rvGames.adapter = resources.data?.let { GamesAdapter(it) }
                }
            }
        }

//        viewModel.getGameById(3498).observe(this) {
//            when(it) {
//                is Resource.Error -> binding.sample.text = "error"
//                is Resource.Loading -> binding.sample.text = "loading"
//                is Resource.Success -> binding.sample.text = it.data?.name
//            }
//            Log.d("gameDetail",it::class.java.simpleName)
//        }
    }
}