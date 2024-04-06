package com.jojobearfitness

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HealthMetricRepository = HealthMetricRepository(AppDatabase.getDatabase(application).healthMetricDao())

    val allHealthMetrics = repository.allHealthMetrics.asLiveData()

    fun insert(healthMetric: HealthMetric) = viewModelScope.launch {
        repository.insert(healthMetric)
    }
}
