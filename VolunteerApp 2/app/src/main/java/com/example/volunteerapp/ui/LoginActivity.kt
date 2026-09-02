package com.example.volunteerapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.volunteerapp.R

/**
 * Login screen: "Create an inspiring login screen for a volunteer app. Use
 * uplifting imagery and a clean layout with fields for email and password,
 * and an encouraging 'Join the Movement' button." (assigned Figma prompt)
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var heroImage: ImageView
    private lateinit var passwordEditText: EditText
    private lateinit var togglePasswordButton: ImageButton
    private lateinit var joinButton: Button

    private var passwordVisible = false

    private val homeScreenIntent by lazy {
        Intent(this, HomeActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        heroImage = findViewById(R.id.hero_image)
        passwordEditText = findViewById(R.id.password_edit_text)
        togglePasswordButton = findViewById(R.id.toggle_password_button)
        joinButton = findViewById(R.id.join_button)

        Glide.with(this)
            .load("https://images.unsplash.com/photo-1559027615-cd4628902d4a?w=860&h=600&fit=crop&auto=format")
            .centerCrop()
            .into(heroImage)
    }

    override fun onResume() {
        super.onResume()

        // Password starts hidden (dots) with an "open eye" icon meaning
        // "tap to reveal". Once revealed, the icon switches to a "closed
        // eye" meaning "tap to hide again" - matches the exported design.
        togglePasswordButton.setOnClickListener {
            passwordVisible = !passwordVisible
            if (passwordVisible) {
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePasswordButton.setImageResource(R.drawable.ic_eye_closed)
            } else {
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePasswordButton.setImageResource(R.drawable.ic_eye_open)
            }
            passwordEditText.setSelection(passwordEditText.text.length)
        }

        // "Join the Movement" takes the user to the Home dashboard.
        joinButton.setOnClickListener {
            startActivity(homeScreenIntent)
            finish()
        }
    }
}
