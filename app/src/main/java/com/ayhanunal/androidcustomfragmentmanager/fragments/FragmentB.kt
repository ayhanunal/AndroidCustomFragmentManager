package com.ayhanunal.androidcustomfragmentmanager.fragments

import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragment
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentType

class FragmentB : MyFragment() {

    override val fragmentType: MyFragmentType
        get() = MyFragmentType.MY_FRAGMENT_TYPE_B

}