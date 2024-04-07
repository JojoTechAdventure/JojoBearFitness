package com.jojobearfitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HealthMetricRepository
    val allHealthMetrics = MutableLiveData<List<HealthMetric>>()

    // MutableLiveDatas for UI text display
    val averageCalories = MutableLiveData<String>()
    val minCalories = MutableLiveData<String>()
    val maxCalories = MutableLiveData<String>()

    init {
        val healthMetricDao = AppDatabase.getDatabase(application).healthMetricDao()
        repository = HealthMetricRepository(healthMetricDao)
        loadMetrics()
    }

    private fun loadMetrics() = viewModelScope.launch {
        repository.allHealthMetrics.collect { metrics ->
            allHealthMetrics.postValue(metrics)
            updateMetricsDisplay(metrics)
        }
    }

    private fun updateMetricsDisplay(metrics: List<HealthMetric>) {
        // Calculate average, min, and max calories from the metrics list
        // Placeholder for actual implementation
        averageCalories.postValue("Calculate average")
        minCalories.postValue("Calculate min")
        maxCalories.postValue("Calculate max")
    }

    fun insert(healthMetric: HealthMetric) = viewModelScope.launch {
        repository.insert(healthMetric)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}
