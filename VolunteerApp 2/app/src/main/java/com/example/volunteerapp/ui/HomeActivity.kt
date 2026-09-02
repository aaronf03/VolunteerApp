package com.example.volunteerapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.volunteerapp.R

/**
 * Home / dashboard screen: "Generate a home screen using Idealist's
 * community-centric design principles. Include a volunteer opportunity
 * search bar and showcase local community projects or global initiatives
 * using a mix of impactful photos and clear call-to-action buttons."
 * (assigned Figma prompt)
 *
 * The opportunity cards themselves are static XML (see activity_home.xml) -
 * this Activity only needs to load their photos and wire up simple click
 * listeners, since the assignment doesn't require a real backend/list.
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var avatarImage: ImageView
    private lateinit var featuredImage: ImageView
    private lateinit var card1Image: ImageView
    private lateinit var card2Image: ImageView

    private lateinit var nearMeTab: TextView
    private lateinit var globalTab: TextView

    private lateinit var saveCard1Button: ImageButton
    private lateinit var saveCard2Button: ImageButton
    private lateinit var signUpCard1Button: View
    private lateinit var signUpCard2Button: View
    private lateinit var navProfile: View

    private val profileScreenIntent by lazy {
        Intent(this, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        avatarImage = findViewById(R.id.avatar_image)
        featuredImage = findViewById(R.id.featured_image)
        card1Image = findViewById(R.id.card_1_image)
        card2Image = findViewById(R.id.card_2_image)

        nearMeTab = findViewById(R.id.near_me_tab)
        globalTab = findViewById(R.id.global_tab)

        saveCard1Button = findViewById(R.id.save_card_1_button)
        saveCard2Button = findViewById(R.id.save_card_2_button)
        signUpCard1Button = findViewById(R.id.sign_up_card_1_button)
        signUpCard2Button = findViewById(R.id.sign_up_card_2_button)
        navProfile = findViewById(R.id.nav_profile)

        loadPhotos()
    }

    override fun onResume() {
        super.onResume()

        nearMeTab.setOnClickListener {
            nearMeTab.setBackgroundResource(R.drawable.bg_pill_forest)
            nearMeTab.setTextColor(getColor(R.color.white))
            globalTab.setBackgroundResource(R.drawable.bg_pill_sand)
            globalTab.setTextColor(getColor(R.color.muted))
        }

        globalTab.setOnClickListener {
            globalTab.setBackgroundResource(R.drawable.bg_pill_forest)
            globalTab.setTextColor(getColor(R.color.white))
            nearMeTab.setBackgroundResource(R.drawable.bg_pill_sand)
            nearMeTab.setTextColor(getColor(R.color.muted))
        }

        signUpCard1Button.setOnClickListener {
            Toast.makeText(this, "Signed up for Community Food Pantry", Toast.LENGTH_SHORT).show()
        }

        signUpCard2Button.setOnClickListener {
            Toast.makeText(this, "Signed up for Urban Garden Cleanup", Toast.LENGTH_SHORT).show()
        }

        // Toggle the save/heart icon between outline and filled on tap.
        saveCard1Button.setOnClickListener { toggleSaveIcon(it as ImageButton) }
        saveCard2Button.setOnClickListener { toggleSaveIcon(it as ImageButton) }

        navProfile.setOnClickListener {
            startActivity(profileScreenIntent)
        }
    }

    private fun loadPhotos() {
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=160&h=160&fit=crop&auto=format")
            .circleCrop()
            .into(avatarImage)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1618477388954-7852f32655ec?w=860&h=400&fit=crop&auto=format")
            .centerCrop()
            .into(featuredImage)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1593113646773-028c64a8f1b8?w=500&h=300&fit=crop&auto=format")
            .centerCrop()
            .into(card1Image)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1466692476868-aef1dfb1e735?w=500&h=300&fit=crop&auto=format")
            .centerCrop()
            .into(card2Image)
    }

    private fun toggleSaveIcon(button: ImageButton) {
        val saved = !button.isSelected
        button.isSelected = saved
        button.setImageResource(if (saved) R.drawable.ic_heart_active else R.drawable.ic_heart)
    }
}
