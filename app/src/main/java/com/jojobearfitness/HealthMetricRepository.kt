package com.jojobearfitness

class HealthMetricRepository(private val healthMetricDao: HealthMetricDao) {
    val allHealthMetrics = healthMetricDao.getAllFoodEntries()

    suspend fun insert(healthMetric: HealthMetric) {
        healthMetricDao.insert(healthMetric)
    }

    suspend fun deleteAll() {
        healthMetricDao.deleteAll()
    }
}
