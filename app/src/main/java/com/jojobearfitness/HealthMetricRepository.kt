package com.jojobearfitness

import kotlinx.coroutines.flow.Flow

class HealthMetricRepository(private val healthMetricDao: HealthMetricDao) {
    val allHealthMetrics: Flow<List<HealthMetric>> = healthMetricDao.getAllFoodEntries()

    suspend fun insert(healthMetric: HealthMetric) {
        healthMetricDao.insert(healthMetric)
    }

    suspend fun deleteAll() {
        healthMetricDao.deleteAll()
    }
}
