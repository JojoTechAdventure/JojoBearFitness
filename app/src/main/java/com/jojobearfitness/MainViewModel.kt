package com.jojobearfitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = HealthMetricRepository(AppDatabase.getDatabase(application).healthMetricDao())
    val allHealthMetrics = MutableLiveData<List<HealthMetric>>()
    val averageCalories = MutableLiveData<String>()
    val minCalories = MutableLiveData<String>()
    val maxCalories = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            repository.allHealthMetrics.collect { metrics ->
                allHealthMetrics.postValue(metrics)
                updateMetricsDisplay(metrics)
            }
        }
    }

    private fun updateMetricsDisplay(metrics: List<HealthMetric>) {
        if (metrics.isNotEmpty()) {
            val sumCalories = metrics.sumOf { it.calories }
            val avgCalories = sumCalories / metrics.size
            val minCal = metrics.minByOrNull { it.calories }?.calories ?: 0
            val maxCal = metrics.maxByOrNull { it.calories }?.calories ?: 0

            averageCalories.postValue(avgCalories.toString())
            minCalories.postValue(minCal.toString())
            maxCalories.postValue(maxCal.toString())
        } else {
            averageCalories.postValue("N/A")
            minCalories.postValue("N/A")
            maxCalories.postValue("N/A")
        }
    }

    fun insert(foodName: String, calories: Int) = viewModelScope.launch {
        if (foodName.isNotBlank() && calories > 0) {
            repository.insert(HealthMetric(foodName, calories))
            // Re-load metrics to update the UI
            repository.allHealthMetrics.collect { metrics ->
                allHealthMetrics.postValue(metrics)
                updateMetricsDisplay(metrics)
            }
        }
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
        // Clear UI metrics after delete all
        allHealthMetrics.postValue(emptyList())
        updateMetricsDisplay(emptyList())
    }
}
