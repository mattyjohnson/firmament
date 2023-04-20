package com.nomaditas.firmament.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.nomaditas.firmament.databinding.FragmentGalleryBinding
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
                binding.progressBar.visibility = View.GONE

                val adapter = GalleryAdapter(it)
                binding.gridMovies.adapter = adapter

                binding.searchBar.addTextChangedListener { filter ->
                    adapter.filter.filter(filter)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
