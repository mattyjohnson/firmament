package com.nomaditas.firmament.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nomaditas.firmament.R
import com.nomaditas.firmament.databinding.FragmentGalleryBinding
import com.nomaditas.firmament.ext.toDp
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment : Fragment() {
    private val model: MovieViewModel by viewModel()

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getMovies().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.apply {
                    progressBar.visibility = View.GONE

                    val adapter = GalleryAdapter(it) { s -> onClick(s) }
                    gridMovies.adapter = adapter

                    searchBar.addTextChangedListener { filter ->
                        adapter.filter.filter(filter)
                    }

                    buttonClearSearch.setOnClickListener {
                        searchBar.text.clear()
                    }

                    adapter.filter.filter(searchBar.text)
                }
            }
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(viewTreeObserver)
    }

    private val viewTreeObserver = ViewTreeObserver.OnGlobalLayoutListener {
        view?.let {
            val spanCount = it.width.toDp / 100 - 1 // TODO: check this relationship works for different screen sizes
            (binding.gridMovies.layoutManager as GridLayoutManager).spanCount = spanCount
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        view?.viewTreeObserver?.removeOnGlobalLayoutListener(viewTreeObserver)
        _binding = null
    }

    private fun onClick(selection: String) {
        val movie = model.getMovie(selection)

        movie?.let {
            val bundle = bundleOf(
                DetailsFragment.KEY_SELECTED_MOVIE to it,
            )

            findNavController().navigate(R.id.action_gallery_to_details, bundle)
        }
    }
}
