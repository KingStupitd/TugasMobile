package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // tampilkan konten di bawah status bar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_main)

        // bind views sesuai id di activity_main.xml
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerText = findViewById<TextView>(R.id.registerText)
        val judul = findViewById<TextView>(R.id.judul)
        val buttonreg = findViewById<TextView>(R.id.registerText)


        // SharedPreferences untuk menyimpan akun sederhana
        val sharedPref = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        var toggleRegisterText = false;

        registerText.setOnClickListener {
            toggleRegisterText = !toggleRegisterText;
            if (toggleRegisterText) {
                judul.text = "Register"
                buttonreg.text = "Sudah punya akun? Login"
            } else {
                judul.text = "Login"
                buttonreg.text = "Sudah punya akun? Register"
            }
        }


        loginButton.setOnClickListener {
            if (toggleRegisterText) {
                val username = usernameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Masukkan username dan password untuk register", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                with(sharedPref.edit()) {
                    putString("username", username)
                    putString("password", password)
                    apply()
                }
                Toast.makeText(this, "Registrasi berhasil! Silakan login.", Toast.LENGTH_SHORT).show()
            } else {
                val username = usernameEditText.text.toString().trim()
                val password = passwordEditText.text.toString().trim()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Masukkan username dan password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val savedUsername = sharedPref.getString("username", null)
                val savedPassword = sharedPref.getString("password", null)

                if (username == savedUsername && password == savedPassword) {
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("EXTRA_USERNAME", username)
                    }
                    Toast.makeText(this, "Login berhasil !", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
