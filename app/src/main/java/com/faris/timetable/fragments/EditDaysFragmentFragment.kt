package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.faris.timetable.R
import com.faris.timetable.databinding.FragmentEditDaysFragmentBinding

class EditDaysFragmentFragment : Fragment() {
    private var _binding : FragmentEditDaysFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDaysFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fabLeft.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_editDaysFragmentFragment_to_editFragment)
        }

        binding.fabRight.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_editDaysFragmentFragment_to_timeTableFragment)
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}