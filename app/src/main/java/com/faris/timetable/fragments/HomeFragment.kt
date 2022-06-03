package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.MainActivity
import com.faris.timetable.adapters.HomeItemAdapter
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentHomeBinding
import com.faris.timetable.viewmodel.HomeViewModel
import com.faris.timetable.viewmodelfactory.HomeViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        (activity as MainActivity).hideActionBar()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val application = requireNotNull(this.activity).application
        val subjectDao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = HomeViewModelFactory(subjectDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.getDayOfTheWeek().observe(viewLifecycleOwner, Observer {
            binding.dayOfWeek.text = it
        })

        val adapter = HomeItemAdapter()
        binding.homeRecView.adapter = adapter



        viewModel.todaySubjects.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })

        return view
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).showActionBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}