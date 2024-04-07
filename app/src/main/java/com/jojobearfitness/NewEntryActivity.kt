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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnRecord.setOnClickListener {
            val foodName = binding.etFoodName.text.toString().trim()
            val calorieString = binding.etCalories.text.toString() 
            viewModel.insert(foodName, calorieString)
            finish()
        }
    }
}
