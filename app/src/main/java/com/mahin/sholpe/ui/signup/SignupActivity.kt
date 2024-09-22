package com.mahin.sholpe.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mahin.sholpe.R
import com.mahin.sholpe.data.remote.ApiClient
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.ui.login.LoginActivity
import com.mahin.sholpe.utils.Resource

class SignupActivity : AppCompatActivity() {

    private lateinit var fullNameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var emailTextInput: EditText
    private lateinit var passwordTextInput: EditText
    private lateinit var signupButton: Button
    private lateinit var loginLink: TextView
    private lateinit var progressBar: ProgressBar

    private val viewModel: SignupViewModel by viewModels {
        SignupViewModelFactory(UserRepository(ApiClient.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullNameInput = findViewById(R.id.fullNameInput)
        phoneInput = findViewById(R.id.phoneInput)
        emailTextInput = findViewById(R.id.emailtextinput)
        passwordTextInput = findViewById(R.id.passwordtextInput)
        signupButton = findViewById(R.id.signupButton)
        progressBar = findViewById(R.id.progressBar)
        loginLink = findViewById(R.id.loginLink)

        signupButton.setOnClickListener {
            val name = fullNameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()
            val email = emailTextInput.text.toString().trim()
            val password = passwordTextInput.text.toString().trim()

            if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registerUser(name, phone, email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        loginLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupResult.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    val message = resource.data?.message ?: "Signup successful"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    // Navigate to Login Page
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Signup Failed: ${resource.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
