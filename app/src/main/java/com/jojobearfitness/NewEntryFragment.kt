package com.jojobearfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jojobearfitness.databinding.FragmentNewEntryBinding
import kotlinx.coroutines.launch

class NewEntryFragment : Fragment() {
    private var _binding: FragmentNewEntryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewEntryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val foodName = binding.etFoodName.text.toString().trim()
            val caloriesText = binding.etCalories.text.toString()
            val calories = caloriesText.toIntOrNull()

            if (foodName.isNotEmpty() && calories != null) {
                val healthMetric = HealthMetric(foodName = foodName, calories = calories)
                lifecycleScope.launch {
                    viewModel.insert(healthMetric)
                    Toast.makeText(requireContext(), "Food recorded successfully", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Please enter a food name and calorie count", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
