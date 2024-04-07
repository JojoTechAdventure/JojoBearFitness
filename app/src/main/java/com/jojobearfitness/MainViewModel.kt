package com.jojobearfitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = HealthMetricRepository(AppDatabase.getDatabase(application).healthMetricDao())

    // No need to convert to LiveData, directly work with Flow
    val allHealthMetricsFlow = repository.allHealthMetrics

    val averageCalories = MutableLiveData<String>()
    val minCalories = MutableLiveData<String>()
    val maxCalories = MutableLiveData<String>()

    init {
        // Collecting the Flow in the init block
        viewModelScope.launch {
            allHealthMetricsFlow.collect { metrics ->
                updateMetricsDisplay(metrics)
            }
        }
    }

    private fun updateMetricsDisplay(metrics: List<HealthMetric>) {
        if (metrics.isNotEmpty()) {
            val totalCalories = metrics.sumOf { it.calories }
            val avgCalories = if (metrics.isNotEmpty()) totalCalories / metrics.size else 0
            val minCal = metrics.minByOrNull { it.calories }?.calories ?: 0
            val maxCal = metrics.maxByOrNull { it.calories }?.calories ?: 0

            // Update LiveData which is observed by UI
            averageCalories.postValue(avgCalories.toString())
            minCalories.postValue(minCal.toString())
            maxCalories.postValue(maxCal.toString())
        } else {
            // Post "N/A" when there are no metrics
            averageCalories.postValue("N/A")
            minCalories.postValue("N/A")
            maxCalories.postValue("N/A")
        }
    }

    fun insert(foodName: String, calorieInput: String) {
        val calories = calorieInput.toIntOrNull() ?: 0
        if (foodName.isNotBlank() && calories > 0) {
            viewModelScope.launch {
                repository.insert(HealthMetric(foodName = foodName, calories = calories))
            }
        } else {
            // Optionally handle invalid input
        }
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}
