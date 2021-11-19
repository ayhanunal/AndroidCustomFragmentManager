package com.ayhanunal.androidcustomfragmentmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayhanunal.androidcustomfragmentmanager.databinding.ActivityMainBinding
import com.ayhanunal.androidcustomfragmentmanager.enumerations.MyFragmentCommand
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentManager
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentType
import com.ayhanunal.androidcustomfragmentmanager.listeners.OnFragmentInteractionListener

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {

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

    override fun onFragmentInteraction(command: MyFragmentCommand, argumentData: Bundle?, addToBackStack: Boolean) {
        var argBundle: Bundle? = argumentData
        if (argBundle == null){
            argBundle = Bundle()
        }

        //default arguments
        argBundle.putBoolean("isUserPushedScreen", true)

        when(command){
            MyFragmentCommand.GO_TO_FRAGMENT_A -> {
                myFragmentManager.setCurrentFragmentType(MyFragmentType.MY_FRAGMENT_TYPE_A, argBundle, addToBackStack)
            }
            MyFragmentCommand.GO_TO_FRAGMENT_B -> {
                myFragmentManager.setCurrentFragmentType(MyFragmentType.MY_FRAGMENT_TYPE_B, argBundle, addToBackStack)
            }
        }
    }

    override fun onBackPressed() {
        if (myFragmentManager.onBackPressed()){
            super.onBackPressed()
        }
    }
}