package com.ayhanunal.androidcustomfragmentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayhanunal.androidcustomfragmentmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}