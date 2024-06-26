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
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRecord.setOnClickListener {
            val foodName = binding.etFoodName.text.toString().trim()
            val calorieInput = binding.etCalories.text.toString().trim()
            if (foodName.isNotEmpty() && calorieInput.isNotEmpty()) {
                viewModel.insert(foodName, calorieInput)
            } else {
                // Optionally show an error message to the user.
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
