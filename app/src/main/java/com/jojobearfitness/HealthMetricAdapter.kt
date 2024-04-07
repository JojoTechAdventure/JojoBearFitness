package com.jojobearfitness

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jojobearfitness.databinding.ItemHealthMetricBinding

class HealthMetricAdapter : ListAdapter<HealthMetric, HealthMetricAdapter.HealthMetricViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthMetricViewHolder {
        val binding = ItemHealthMetricBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthMetricViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthMetricViewHolder, position: Int) {
        val currentHealthMetric = getItem(position)
        holder.bind(currentHealthMetric)
    }

    class HealthMetricViewHolder(private val binding: ItemHealthMetricBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(healthMetric: HealthMetric) {
            binding.tvFoodName.text = healthMetric.foodName
            binding.tvCalories.text = "${healthMetric.calories} Calories"
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<HealthMetric>() {
        override fun areItemsTheSame(oldItem: HealthMetric, newItem: HealthMetric): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HealthMetric, newItem: HealthMetric): Boolean {
            return oldItem == newItem
        }
    }
}
