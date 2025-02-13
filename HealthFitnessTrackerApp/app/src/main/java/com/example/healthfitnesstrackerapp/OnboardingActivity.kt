package com.example.healthfitnesstrackerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button
import com.example.healthfitnesstrackerapp.adapter.OnboardingAdapter

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var skipButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // List of fitness tips
        val fitnessTips = listOf(
            "Stay hydrated, drink plenty of water!",
            "Exercise regularly to stay fit and healthy.",
            "Eat a balanced diet for overall well-being."
        )

        // Initialize the ViewPager2 with an adapter
        viewPager = findViewById(R.id.viewPager)
        val adapter = OnboardingAdapter(fitnessTips)
        viewPager.adapter = adapter

        // Initialize the Skip button
        skipButton = findViewById(R.id.skipButton)

        // Set OnClickListener for Skip button
        skipButton.setOnClickListener {
            navigateToDashboard()
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        navigateToDashboard()  // Navigate after 2 seconds
                    }, 2000)
                }
            }

        })
    }

    private fun navigateToDashboard() {
        // Navigate to the Dashboard screen (for example, MainActivity)
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}