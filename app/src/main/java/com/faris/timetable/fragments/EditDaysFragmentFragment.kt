package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.faris.timetable.R
import com.faris.timetable.adapters.EditDaysItemAdapter
import com.faris.timetable.adapters.EditItemAdapter
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentEditDaysFragmentBinding
import com.faris.timetable.viewmodel.EditDaysViewModel
import com.faris.timetable.viewmodelfactory.EditDaysViewModelFactory

class EditDaysFragmentFragment : Fragment() {
    private var _binding: FragmentEditDaysFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDaysFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val application = requireNotNull(this.activity).application
        val subjectDao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = EditDaysViewModelFactory(subjectDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditDaysViewModel::class.java)



        viewModel.allSubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.getAllOptions()
            }
        })

        binding.buttonMonday.setOnClickListener {
            val dayName = binding.spinnerMonday.selectedItem.toString()
            if (dayName != "Predmet")
                viewModel.addSubjectToDay(dayName, 1)
            else
                Toast.makeText(context, "Molimo izaberite predmet!", Toast.LENGTH_SHORT).show()
        }

        binding.buttonTuesday.setOnClickListener {
            val dayName = binding.spinnerTuesday.selectedItem.toString()
            if (dayName != "Predmet")
                viewModel.addSubjectToDay(dayName, 2)
            else
                Toast.makeText(context, "Molimo izaberite predmet!", Toast.LENGTH_SHORT).show()
        }

        binding.buttonWednesday.setOnClickListener {
            val dayName = binding.spinnerWednesday.selectedItem.toString()
            if (dayName != "Predmet")
                viewModel.addSubjectToDay(dayName, 3)
            else
                Toast.makeText(context, "Molimo izaberite predmet!", Toast.LENGTH_SHORT).show()
        }

        binding.buttonThursday.setOnClickListener {
            val dayName = binding.spinnerThursday.selectedItem.toString()
            if (dayName != "Predmet")
                viewModel.addSubjectToDay(dayName, 4)
            else
                Toast.makeText(context, "Molimo izaberite predmet!", Toast.LENGTH_SHORT).show()
        }

        binding.buttonFriday.setOnClickListener {
            val dayName = binding.spinnerFriday.selectedItem.toString()
            if (dayName != "Predmet")
                viewModel.addSubjectToDay(dayName, 5)
            else
                Toast.makeText(context, "Molimo izaberite predmet!", Toast.LENGTH_SHORT).show()
        }
        val mondayAdapter = EditDaysItemAdapter {
            viewModel.deleteSubject(it)
        }

        val tuesdayAdapter = EditDaysItemAdapter {
            viewModel.deleteSubject(it)
        }

        val wednesdayAdapter = EditDaysItemAdapter {
            viewModel.deleteSubject(it)
        }

        val thursdayAdapter = EditDaysItemAdapter {
            viewModel.deleteSubject(it)
        }

        val fridayAdapter = EditDaysItemAdapter {
            viewModel.deleteSubject(it)
        }

        binding.mondayRecView.adapter = mondayAdapter
        binding.tuesdayRecView.adapter = tuesdayAdapter
        binding.wednesdayRecView.adapter = wednesdayAdapter
        binding.thursdayRecView.adapter = thursdayAdapter
        binding.fridayRecView.adapter = fridayAdapter

        viewModel.mondaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                mondayAdapter.submitList(it)
            }
        })

        viewModel.tuesdaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                tuesdayAdapter.submitList(it)
            }
        })

        viewModel.wednesdaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                wednesdayAdapter.submitList(it)
            }
        })

        viewModel.thursdaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                thursdayAdapter.submitList(it)
            }
        })

        viewModel.fridaySubjects.observe(viewLifecycleOwner, Observer {
            it?.let {
                fridayAdapter.submitList(it)
            }
        })


        viewModel.allOptionsList.observe(viewLifecycleOwner, Observer {
            it?.let {
                val adapter = ArrayAdapter<String>(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    it
                )
                binding.spinnerMonday.adapter = adapter
                binding.spinnerTuesday.adapter = adapter
                binding.spinnerWednesday.adapter = adapter
                binding.spinnerThursday.adapter = adapter
                binding.spinnerFriday.adapter = adapter
            }
        })





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