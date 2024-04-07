package com.jojobearfitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    // Initialize the repository immediately, not inside init block
    private val healthMetricDao = AppDatabase.getDatabase(application).healthMetricDao()
    private val repository = HealthMetricRepository(healthMetricDao)
    val allHealthMetrics = MutableLiveData<List<HealthMetric>>()

    // MutableLiveDatas for UI text display
    val averageCalories = MutableLiveData<String>()
    val minCalories = MutableLiveData<String>()
    val maxCalories = MutableLiveData<String>()

    init {
        // Loading metrics when the ViewModel is created
        loadMetrics()
    }

    private fun loadMetrics() = viewModelScope.launch {
        repository.allHealthMetrics.collect { metrics ->
            allHealthMetrics.postValue(metrics)
            updateMetricsDisplay(metrics)
        }
    }

    private fun updateMetricsDisplay(metrics: List<HealthMetric>) {
        // Placeholder for actual calculations
        averageCalories.postValue("Calculate average")
        minCalories.postValue("Calculate min")
        maxCalories.postValue("Calculate max")
    }

    fun insert(foodName: String, calorieString: String) = viewModelScope.launch {
        val calories = calorieString.toIntOrNull()
        if (foodName.isNotBlank() && calories != null) {
            repository.insert(HealthMetric(foodName = foodName, calories = calories))
        } else {
            // Handle invalid input
        }
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

