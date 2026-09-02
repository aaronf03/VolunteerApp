package com.example.volunteerapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.volunteerapp.R

/**
 * Profile screen: "Design a user profile screen inspired by Idealist's
 * impact-focused layout. Feature the user's volunteer history and impact
 * statistics using meaningful infographics and cause-related imagery."
 * (assigned Figma prompt)
 *
 * All of the text on this screen (stats, impact-goal percentages, cause
 * breakdown, volunteer history) is static XML in activity_profile.xml -
 * this Activity only needs to load the photos and wire up simple clicks.
 */
class ProfileActivity : AppCompatActivity() {

    private lateinit var coverImage: ImageView
    private lateinit var avatarImage: ImageView
    private lateinit var history1Image: ImageView
    private lateinit var history2Image: ImageView
    private lateinit var history3Image: ImageView

    private lateinit var backButton: View
    private lateinit var navHome: View

    private val homeScreenIntent by lazy {
        Intent(this, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        coverImage = findViewById(R.id.cover_image)
        avatarImage = findViewById(R.id.avatar_image)
        history1Image = findViewById(R.id.history_1_image)
        history2Image = findViewById(R.id.history_2_image)
        history3Image = findViewById(R.id.history_3_image)

        backButton = findViewById(R.id.back_button)
        navHome = findViewById(R.id.nav_home)

        loadPhotos()
    }

    override fun onResume() {
        super.onResume()

        backButton.setOnClickListener { goToHome() }
        navHome.setOnClickListener { goToHome() }
        // The share icon and "Share Profile" pill are visual only - wiring
        // up Android's real share sheet (Intent.ACTION_SEND) is outside
        // the scope of this assignment.
    }

    private fun loadPhotos() {
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1593113646773-028c64a8f1b8?w=900&h=400&fit=crop&auto=format")
            .centerCrop()
            .into(coverImage)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200&h=200&fit=crop&auto=format")
            .circleCrop()
            .into(avatarImage)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1541888946425-d81bb19240f5?w=200&h=200&fit=crop&auto=format")
            .centerCrop()
            .into(history1Image)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1593113646773-028c64a8f1b8?w=200&h=200&fit=crop&auto=format")
            .centerCrop()
            .into(history2Image)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1618477461853-cf6ed80faba5?w=200&h=200&fit=crop&auto=format")
            .centerCrop()
            .into(history3Image)
    }

    private fun goToHome() {
        startActivity(homeScreenIntent)
        finish()
    }
}
