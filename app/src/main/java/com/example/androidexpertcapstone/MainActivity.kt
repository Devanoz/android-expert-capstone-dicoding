package com.example.androidexpertcapstone

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
                }

                is Resource.Loading -> {
                    Log.d("resourceStatus", "games is loading")
                }

                is Resource.Success -> {
                    rvGames.adapter = resources.data?.let { GamesAdapter(it) }
                }

                else -> {}
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar,menu)
        val searchItem = topAppBar.menu.findItem(R.id.search_view)
        val searchView = searchItem.actionView as SearchView

        RxSearchView.queryTextChanges(searchView)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe {
                Log.d("searchViewItem",it.toString())
                val query = it.toString()
                viewModel.searchGameByName(query)
            }
        return true
    }

    @SuppressLint("CheckResult")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favorite_menu -> {

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}