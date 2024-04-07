package com.jojobearfitness

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jojobearfitness.databinding.ActivityNewEntryBinding

class NewEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewEntryBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.btnRecord.setOnClickListener {
            val foodName = binding.etFoodName.text.toString().trim()
            val calories = binding.etCalories.text.toString().toIntOrNull()
            if (foodName.isNotEmpty() && calories != null) {
                val healthMetric = HealthMetric(foodName = foodName, calories = calories)
                viewModel.insert(healthMetric)
                finish()
            } else {
                // Handle error
            }
        }
    }
}
