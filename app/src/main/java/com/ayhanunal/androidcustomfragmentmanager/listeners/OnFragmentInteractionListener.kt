package com.ayhanunal.androidcustomfragmentmanager.listeners

import android.os.Bundle
import com.ayhanunal.androidcustomfragmentmanager.enumerations.MyFragmentCommand

interface OnFragmentInteractionListener {

    fun onFragmentInteraction(command: MyFragmentCommand, argumentData: Bundle? = null, addToBackStack: Boolean)

}