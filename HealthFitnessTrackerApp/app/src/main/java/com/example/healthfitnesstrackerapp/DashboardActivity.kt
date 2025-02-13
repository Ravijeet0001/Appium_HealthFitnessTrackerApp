package com.example.healthfitnesstrackerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.widget.Button
import android.widget.TextView

class DashboardActivity : AppCompatActivity() {

    private var steps: Int = 5000
    private var calories: Int = 300
    private var status:String ="Data sync successfully"
    private lateinit var btnSync: Button
    private lateinit var tvSyncStatus: TextView
    private lateinit var tvSteps: TextView
    private lateinit var tvCalories: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialize views
        tvSteps = findViewById(R.id.tvSteps)
        tvCalories = findViewById(R.id.tvCalories)
        tvSyncStatus = findViewById(R.id.tvSyncStatus)
        btnSync = findViewById(R.id.btnSync)

        updateMetrics()

        btnSync.setOnClickListener {
            syncData()

        }
    }

    private fun updateMetrics() {
        tvSteps.text = "Steps Taken: $steps"
        tvCalories.text = "Calories Burned: $calories"
    }
    private fun syncData(){
        tvSyncStatus.text = "$status"
        tvSyncStatus.visibility=android.view.View.VISIBLE
    }


}