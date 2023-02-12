package com.example.fit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fit.R
import com.example.fit.adapters.BodyAdapter
import com.example.fit.data.Body
import com.example.fit.databinding.FragmentBodyBinding
import com.example.fit.viewmodel.BodyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BodyFragment : Fragment() {

    private var _binding:FragmentBodyBinding? = null
    private val binding get() = _binding

    private val bodyViewModel:BodyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBodyBinding.bind(view)
        val adapter = BodyAdapter(requireContext())
        val layoutManager = LinearLayoutManager(requireContext())
        val recyclerView = binding?.recyclerViewBody

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        val currentMeasurement = bodyViewModel.lastMeasurement
        val previousMeasurement = bodyViewModel.previous()
        if (currentMeasurement != null && previousMeasurement!= null ) {
            adapter.setMeasurements(currentMeasurement,previousMeasurement)
        }

        binding?.addMeasurementFloatingButton?.setOnClickListener {
            val fm = childFragmentManager
            val addMeasurementsBodyFragment = AddBodyMeasurementsFragment()
            addMeasurementsBodyFragment.show(fm,"AlertDialog")
        }


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_body, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}