package com.jojobearfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jojobearfitness.databinding.FragmentNewEntryBinding

class NewEntryFragment : Fragment() {
    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false).apply {
            viewModel = this@NewEntryFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
