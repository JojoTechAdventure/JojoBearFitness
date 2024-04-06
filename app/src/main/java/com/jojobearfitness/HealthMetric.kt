package com.jojobearfitness

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_metric")
data class HealthMetric(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "food_name") val foodName: String,
    @ColumnInfo(name = "calories") val calories: Int
)
