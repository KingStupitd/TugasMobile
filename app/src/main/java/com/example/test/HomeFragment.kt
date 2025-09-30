package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * HomeFragment: fragmen sederhana untuk tampilan beranda.
 * - Tempat menaruh ringkasan, banner, atau komponen lain.
 * - Di sini saya sisakan layout kosong dengan teks penjelasan.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_home.xml
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
