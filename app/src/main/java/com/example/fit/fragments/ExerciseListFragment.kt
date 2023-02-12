package com.example.fit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fit.R
import com.example.fit.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.adapters.ExerciseAdapter
import com.example.fit.databinding.FragmentExerciseListBinding

@AndroidEntryPoint
class ExerciseListFragment : Fragment() {

    private val exerciseViewModel:ExerciseViewModel by viewModels()
    private var _binding:FragmentExerciseListBinding? = null
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentExerciseListBinding.bind(view)
        val bodyIcon = binding?.bodyIcon
        bodyIcon?.setOnClickListener {
                findNavController().navigate(R.id.action_exerciseListFragment_to_bodyFragment)
        }
        val adapter = ExerciseAdapter(requireContext())
        val recyclerView = binding?.recyclerView
        adapter.setCategory(exerciseViewModel.allExercise)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = GridLayoutManager(requireActivity(), 2)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_exercise_list, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}