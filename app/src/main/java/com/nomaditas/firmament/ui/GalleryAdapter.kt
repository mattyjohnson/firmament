package com.nomaditas.firmament.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nomaditas.firmament.databinding.ItemMoviePosterBinding
import com.nomaditas.firmament.domain.Movie
import java.util.*

class GalleryAdapter(
    private val data: List<Movie>,
) : RecyclerView.Adapter<GalleryAdapter.AccountOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviePosterBinding.inflate(inflater, parent, false)
        return AccountOrderViewHolder(binding)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: AccountOrderViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class AccountOrderViewHolder(
        private val binding: ItemMoviePosterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textMovieTitle.text = movie.title
            Glide.with(binding.root).load(movie.poster).into(binding.imgMoviePoster)
        }
    }
}
