package com.ayhanunal.androidcustomfragmentmanager.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayhanunal.androidcustomfragmentmanager.databinding.FragmentBBinding
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragment
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentType

class FragmentB : MyFragment() {

    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isUserPushed = arguments?.get("isUserPushedScreen")
        Log.e("TEST", "Fragment B isUserPushed: ${isUserPushed}")

    }

    override val fragmentType: MyFragmentType
        get() = MyFragmentType.MY_FRAGMENT_TYPE_B

}