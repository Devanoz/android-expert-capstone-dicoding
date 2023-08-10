package com.example.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.FavouriteGamesItemBinding
import com.example.core.domain.model.Game

class FavouriteGamesAdapter(private val games: List<Game>,private val onFavoriteClick: (gameId: Int,isFavorite: Boolean)->Unit): RecyclerView.Adapter<FavouriteGamesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FavouriteGamesItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(game: Game) {
            Glide.with(binding.root.context).load(game.backgroundImageUrl).into(binding.imvGameImage)
            binding.tvGameName.text = game.name
            var isFavorite = game.isFavorite
            binding.imvFavorite.setOnClickListener {
                isFavorite = !isFavorite
                onFavoriteClick(game.id,isFavorite)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavouriteGamesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size
}