package com.jojobearfitness

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class NewEntryActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_entry)

        // The ViewModel is instantiated using the ViewModelProvider
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Fetching the EditText and Button views from the layout
        val etFoodName = findViewById<EditText>(R.id.etFoodName)
        val etCalories = findViewById<EditText>(R.id.etCalories)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val foodName = etFoodName.text.toString().trim()
            val caloriesText = etCalories.text.toString()
            // Parse the calories input to an integer
            val calories = caloriesText.toIntOrNull()

            // Check if the input is valid
            if (foodName.isNotEmpty() && calories != null) {
                // Create a new HealthMetric object with the input data
                val healthMetric = HealthMetric(foodName = foodName, calories = calories)

                // Launch a coroutine in the scope of the activity to perform the database insert
                lifecycleScope.launch {
                    viewModel.insert(healthMetric)
                    Toast.makeText(this@NewEntryActivity, "Food recorded successfully", Toast.LENGTH_SHORT).show()
                    finish() // Finish this activity and go back to the previous one
                }
            } else {
                // If the input is not valid, show a toast message
                Toast.makeText(this, "Please enter a food name and calorie count", Toast.LENGTH_LONG).show()
            }
        }
    }
}
