package com.jojobearfitness

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HealthMetricDao {
    @Query("SELECT * FROM health_metric ORDER BY id DESC")
    fun getAllFoodEntries(): LiveData<List<HealthMetric>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(healthMetric: HealthMetric)

    @Query("DELETE FROM health_metric")
    suspend fun deleteAll()
}
