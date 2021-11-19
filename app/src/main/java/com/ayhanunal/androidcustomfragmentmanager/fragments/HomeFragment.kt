package com.ayhanunal.androidcustomfragmentmanager.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ayhanunal.androidcustomfragmentmanager.databinding.FragmentHomeBinding
import com.ayhanunal.androidcustomfragmentmanager.enumerations.MyFragmentCommand
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragment
import com.ayhanunal.androidcustomfragmentmanager.fragments.infrastructure.MyFragmentType
import com.ayhanunal.androidcustomfragmentmanager.listeners.OnFragmentInteractionListener
import java.lang.RuntimeException

class HomeFragment : MyFragment() {

    private lateinit var binding: FragmentHomeBinding

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isUserPushed = arguments?.get("isUserPushedScreen")
        Log.e("TEST", "Fragment Home isUserPushed: ${isUserPushed}")

        binding.homeFragmentGotoFragmentA.setOnClickListener {
            mListener?.onFragmentInteraction(MyFragmentCommand.GO_TO_FRAGMENT_A, null, true)
        }

        binding.homeFragmentGotoFragmentB.setOnClickListener {
            mListener?.onFragmentInteraction(MyFragmentCommand.GO_TO_FRAGMENT_B, null, true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            mListener = context
        }else{
            throw RuntimeException("${context} must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override val fragmentType: MyFragmentType
        get() = MyFragmentType.HOME_FRAGMENT

}