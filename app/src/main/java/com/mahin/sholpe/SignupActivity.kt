package com.mahin.sholpe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var fullNameInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var emailtextinput: EditText
    private lateinit var passwordtextInput: EditText
    private lateinit var signupButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        fullNameInput = findViewById(R.id.fullNameInput)
        phoneInput = findViewById(R.id.phoneInput)
        emailtextinput = findViewById(R.id.emailtextinput)
        passwordtextInput = findViewById(R.id.passwordtextInput)
        signupButton = findViewById(R.id.signupButton)
        progressBar = findViewById(R.id.progressBar)

        signupButton.setOnClickListener {
            val name = fullNameInput.text.toString()
            val phone = phoneInput.text.toString()
            val email = emailtextinput.text.toString()
            val password = passwordtextInput.text.toString()


            if (name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(name, phone, email, password)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(name: String, phone: String, email: String, password: String) {
        progressBar.visibility = View.VISIBLE

        val signupRequest =
            data.SignupRequest(name = name, phone = phone, email = email, password = password)
        RetrofitInstance.api.registerUser(signupRequest).enqueue(object : Callback<data.SignupResponse> {
            override fun onResponse(call: Call<data.SignupResponse>, response: Response<data.SignupResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    val signupResponse = response.body()!!
                    Toast.makeText(this@SignupActivity, signupResponse.message, Toast.LENGTH_SHORT).show()
                    // Navigate to Login Page
                    val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@SignupActivity, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<data.SignupResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@SignupActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
