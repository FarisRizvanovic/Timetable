package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.adapters.HomeItemAdapter
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentDayOfTheWeekSubjectsBinding
import com.faris.timetable.viewmodel.DayOfTheWeekSubjectsVIewModel
import com.faris.timetable.viewmodelfactory.DayOfTheWeekSubjectsViewModelFactory

class DayOfTheWeekSubjectsFragment(private val dayId: Int = 1) : Fragment() {
    private var _binding: FragmentDayOfTheWeekSubjectsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDayOfTheWeekSubjectsBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = DayOfTheWeekSubjectsViewModelFactory(dao, dayId)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(DayOfTheWeekSubjectsVIewModel::class.java)

        val adapter = HomeItemAdapter()
        binding.subjectsEachDayRec.adapter = adapter

        viewModel.todaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
                if (it.isNotEmpty()) {
                    binding.addSubjectsByEditText.visibility = View.GONE
                    binding.subjectsEachDayRec.visibility = View.VISIBLE
                }
                if (it.isEmpty()) {
                    binding.addSubjectsByEditText.visibility = View.VISIBLE
                    binding.subjectsEachDayRec.visibility = View.GONE
                }
            }
        })

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}