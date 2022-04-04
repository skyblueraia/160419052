package com.example.s160419052_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.s160419052_advancenativeuts.R
import kotlinx.android.synthetic.main.fragment_setting.*

class FragmentSetting : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSettingLogin.setOnClickListener{
            val action = FragmentSettingDirections.actionFragmentSettingToFragmentLogin()
            Navigation.findNavController(it).navigate(action)
        }

        btnSettingRegister.setOnClickListener{
            val action = FragmentSettingDirections.actionFragmentSettingToFragmentRegister()
            Navigation.findNavController(it).navigate(action)
        }
    }
}