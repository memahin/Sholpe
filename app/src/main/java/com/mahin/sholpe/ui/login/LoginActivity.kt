package com.mahin.sholpe.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mahin.sholpe.R
import com.mahin.sholpe.data.remote.ApiClient
import com.mahin.sholpe.data.repository.UserRepository
import com.mahin.sholpe.ui.main.MainActivity
import com.mahin.sholpe.ui.signup.SignupActivity
import com.mahin.sholpe.utils.Resource

class LoginActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var progressBar: ProgressBar

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(UserRepository(ApiClient.api))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        signupButton = findViewById(R.id.signupButton)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.loginUser(email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    // Navigate to Product Page
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, "Login Failed: ${resource.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}