package com.jojobearfitness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jojobearfitness.databinding.ItemHealthMetricBinding

class HealthMetricAdapter : RecyclerView.Adapter<HealthMetricAdapter.HealthMetricViewHolder>() {
    private var healthMetrics: List<HealthMetric> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthMetricViewHolder {
        val binding = ItemHealthMetricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthMetricViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthMetricViewHolder, position: Int) {
        val currentHealthMetric = healthMetrics[position]
        holder.binding.foodNameTextView.text = currentHealthMetric.foodName
        holder.binding.caloriesTextView.text = "${currentHealthMetric.calories} Calories"
    }

    override fun getItemCount(): Int = healthMetrics.size

    fun updateData(newHealthMetrics: List<HealthMetric>) {
        healthMetrics = newHealthMetrics
        notifyDataSetChanged()
    }

    class HealthMetricViewHolder(val binding: ItemHealthMetricBinding) : RecyclerView.ViewHolder(binding.root)
}
