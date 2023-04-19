package com.nomaditas.firmament.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            for (m in it) {
                Log.i("MJJ", m.Title)
                Log.i("MJJ", m.Actors)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
