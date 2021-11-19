package com.ayhanunal.androidcustomfragmentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayhanunal.androidcustomfragmentmanager.databinding.ActivityMainBinding
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentManager
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentType

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myFragmentManager = MyFragmentManager(this, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initFragment()
    }

    private fun initFragment(){
        myFragmentManager.setCurrentFragmentType(MyFragmentType.HOME_FRAGMENT, null, false)
    }
}