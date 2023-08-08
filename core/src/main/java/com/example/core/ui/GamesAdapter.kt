package com.example.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.GamesItemBinding
import com.example.core.domain.model.Game

class GamesAdapter(private val games: List<Game>): RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: GamesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            Glide.with(binding.root.context).load(game.backgroundImageUrl).into(binding.imvGameImage)
            binding.tvGameName.text = game.name
            binding.root.setOnClickListener {
                //goto detail activity and bring game id
                val intent = Intent(binding.root.context,Class.forName("com.example.androidexpertcapstone.detail.GameDetailActivity"))
                intent.putExtra(KEY_GAME_ID,game.id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GamesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size

    companion object {
        const val KEY_GAME_ID = "keyGameId"
    }
}