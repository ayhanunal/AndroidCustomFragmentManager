package com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ayhanunal.androidcustomfragmentmanager.R
import com.ayhanunal.androidcustomfragmentmanager.fragments.FragmentA
import com.ayhanunal.androidcustomfragmentmanager.fragments.FragmentB
import java.lang.Exception
import java.util.*

/**
 * Created by ayhanunal on 18.11.2021.
 */

class MyFragmentManager(private val context: Context, private val fragmentManager: FragmentManager) {

    private var currentFragment: MyFragment? = null
    private var baseFragment: MyFragment? = null

    private val myFragmentStack = Stack<MyFragment>()

    private fun getScreenName(type: MyFragmentType) : String {
        return when(type) {
            MyFragmentType.MY_FRAGMENT_TYPE_A -> "fragment_a"
            MyFragmentType.MY_FRAGMENT_TYPE_B -> "fragment_b"
        }
    }

    fun setCurrentFragmentType(fragmentType: MyFragmentType, arguments: Bundle?, addToBackStack: Boolean) {

        if (currentFragment?.fragmentType === fragmentType) return

        if (!addToBackStack) clearBackStack()

        currentFragment = when (fragmentType) {
            MyFragmentType.MY_FRAGMENT_TYPE_A -> FragmentA()
            MyFragmentType.MY_FRAGMENT_TYPE_B -> FragmentB()
        }

        currentFragment?.arguments = arguments

        if (addToBackStack){
            myFragmentStack.add(currentFragment)
        }else{
            baseFragment = currentFragment
        }

        val fragmentTransaction = fragmentManager.beginTransaction()
        if (addToBackStack){
            fragmentTransaction.add(R.id.activity_main_fragment_container, currentFragment as Fragment)
                .addToBackStack(fragmentType.toString())
        }else{
            fragmentTransaction.replace(R.id.activity_main_fragment_container, currentFragment as Fragment)
        }

        try {
            fragmentTransaction.commit()
        }catch (e: Exception){
            e.printStackTrace()
        }

    }

    fun isBackStackEmpty(): Boolean {
        return myFragmentStack.isEmpty()
    }

    fun clearBackStack() {
        currentFragment = baseFragment
        myFragmentStack.clear()

        if (fragmentManager.backStackEntryCount <= 0) {
            return
        }

        try {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun onBackPressed(): Boolean {

        if (isBackStackEmpty()) {
            return true
        }

        myFragmentStack.pop()
        currentFragment = if (myFragmentStack.isEmpty())
            baseFragment
        else
            myFragmentStack.peek()

        return true
    }

}