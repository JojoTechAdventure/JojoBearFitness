package com.jojobearfitness

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthMetricDao {
    @Query("SELECT * FROM health_metric")
    fun getAllFoodEntries(): Flow<List<HealthMetric>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(healthMetric: HealthMetric)

    @Query("DELETE FROM health_metric")
    suspend fun deleteAll()
}
