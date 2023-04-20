package com.nomaditas.firmament.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.nomaditas.firmament.R
import com.nomaditas.firmament.databinding.ItemMoviePosterBinding
import com.nomaditas.firmament.domain.Movie
import java.util.*

class GalleryAdapter(
    private val data: List<Movie>,
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>(), Filterable {

    private lateinit var circularProgressDrawable: CircularProgressDrawable
    val resultsList = ArrayList(data)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        circularProgressDrawable = CircularProgressDrawable(recyclerView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.setColorSchemeColors(recyclerView.context.getColor(R.color.white))
        circularProgressDrawable.start()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoviePosterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultsList[position])
    }

    override fun getItemCount(): Int = resultsList.size

    override fun getFilter() = textFilter

    private val textFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Movie> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(data)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in resultsList) {
                    if (item.title.lowercase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    } else if (item.genre.lowercase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            resultsList.clear()
            resultsList.addAll(results.values as List<Movie>)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(
        private val binding: ItemMoviePosterBinding,
        val onClick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.textMovieTitle.text = movie.title
            Glide.with(binding.root)
                .load(movie.poster)
                .placeholder(circularProgressDrawable)
                .into(binding.imgMoviePoster)

            itemView.setOnClickListener {
                onClick(movie.title)
            }
        }
    }
}
