package com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure

import androidx.fragment.app.Fragment

abstract class MyFragment : Fragment() {

    abstract val fragmentType: MyFragmentType

}