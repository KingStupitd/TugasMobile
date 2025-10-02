package com.example.test

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // tampilkan konten di bawah status bar seperti di MainActivity
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(R.layout.activity_home)

        // bind views yang ada di activity (top bar)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val btnNotif = findViewById<ImageButton>(R.id.btnNotif)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // contoh: tangani event pencarian dari keyboard (actionSearch)
        searchEditText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString().trim()
                if (query.isEmpty()) {
                    Toast.makeText(this, "Masukkan kata kunci pencarian", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Mencari: $query", Toast.LENGTH_SHORT).show()
                    // TODO: beritahu fragment produk untuk melakukan filter/pencarian jika diinginkan
                }
                true
            } else false
        }

        // contoh: klik Notif
        btnNotif.setOnClickListener {
            Toast.makeText(this, "Tombol Notifikasi diklik", Toast.LENGTH_SHORT).show()
            // TODO: buka activity notifikasi atau tampilkan dialog
        }

        // Setup BottomNavigation: ketika item dipilih, ganti fragment di fragmentContainer
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, HomeFragment())
                        .commit()
                    true
                }
                R.id.nav_product -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ProductFragment())
                        .commit()
                    true
                }
                R.id.nav_account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, AccountFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        // Set default menu
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_product
        }
    }
}
