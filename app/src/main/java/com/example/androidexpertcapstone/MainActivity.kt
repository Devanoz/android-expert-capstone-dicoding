package com.example.androidexpertcapstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidexpertcapstone.databinding.ActivityMainBinding
import com.example.core.data.Resource
import com.example.core.ui.GamesAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.jakewharton.rxbinding2.widget.RxSearchView
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvGames: RecyclerView

    private lateinit var topAppBar: MaterialToolbar

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        topAppBar = binding.topAppBar
        setSupportActionBar(topAppBar)

        rvGames = binding.rvGames
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.games.observe(this) { resources ->
            Log.d("gameList", resources::class.java.simpleName)
            when (resources) {
                is Resource.Error -> {
                    Toast.makeText(this, "api error", Toast.LENGTH_SHORT).show()
                    setLoadingState(false)
                }

                is Resource.Loading -> {
                    Log.d("resourceStatus", "games is loading")
                    setLoadingState(true)
                }

                is Resource.Success -> {
                    rvGames.adapter = resources.data?.let { GamesAdapter(it) }
                    setLoadingState(false)
                }

                else -> {}
            }
        }
    }

    private fun setLoadingState(isVisible: Boolean) {
        if (isVisible) {
            binding.loadingIndicator.visibility = View.VISIBLE
        } else {
            binding.loadingIndicator.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        val searchItem = topAppBar.menu.findItem(R.id.search_view)
        val searchView = searchItem.actionView as SearchView

         RxSearchView.queryTextChanges(searchView)
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe {
                Log.d("searchViewItem", it.toString())
                val query = it.toString()
                viewModel.searchGameByName(query)
            }
        return true
    }

    @SuppressLint("CheckResult")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                val intent = Intent(this, Class.forName("com.example.favourite.FavouriteActivity"))
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}