package com.nomaditas.firmament.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nomaditas.firmament.databinding.FragmentDetailsBinding
import com.nomaditas.firmament.domain.Movie

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { it ->
            val selected = it.getParcelable(KEY_SELECTED_MOVIE, Movie::class.java)

            binding.apply {
                Glide.with(binding.root)
                    .load(selected?.poster)
                    .into(binding.imagePoster)

                textTitle.text = selected?.title
                textGenre.text = selected?.genre
                textYear.text = selected?.year
                textPlot.text = selected?.plot
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_SELECTED_MOVIE = "key_selected_movie"
    }
}
