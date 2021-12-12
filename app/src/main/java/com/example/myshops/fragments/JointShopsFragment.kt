package com.example.myshops.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myshops.databinding.FragmentJointShopsBinding


class JointShopsFragment : Fragment() {

   lateinit var binding : FragmentJointShopsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentJointShopsBinding.inflate(inflater)

        return binding.root
    }


}
