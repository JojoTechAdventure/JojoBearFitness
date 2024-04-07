package com.jojobearfitness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jojobearfitness.databinding.FragmentLogBinding

class LogFragment : Fragment() {
    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var healthMetricAdapter: HealthMetricAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()

        binding.btnAddNewFood.setOnClickListener {
            findNavController().navigate(R.id.action_logFragment_to_newEntryFragment)
        }

        binding.btnClearData.setOnClickListener {
            viewModel.clearHealthMetrics()
        }
    }

    private fun setupRecyclerView() {
        healthMetricAdapter = HealthMetricAdapter()
        binding.rvHealthMetrics.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = healthMetricAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.allHealthMetrics.observe(viewLifecycleOwner) { healthMetrics ->
            healthMetricAdapter.submitList(healthMetrics)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
