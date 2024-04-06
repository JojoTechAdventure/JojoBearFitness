package com.jojobearfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojobearfitness.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your HealthMetricAdapter
        val adapter = HealthMetricAdapter()

        // Set up the RecyclerView
        binding.healthMetricsList.layoutManager = LinearLayoutManager(context)
        binding.healthMetricsList.adapter = adapter

        // Observe the LiveData from your ViewModel
        viewModel.allHealthMetrics.observe(viewLifecycleOwner) { healthMetrics ->
            // Whenever data changes, update your adapter
            adapter.updateData(healthMetrics)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

