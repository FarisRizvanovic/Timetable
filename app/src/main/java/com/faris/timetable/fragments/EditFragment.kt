package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.faris.timetable.R
import com.faris.timetable.adapters.EditItemAdapter
import com.faris.timetable.adapters.HomeItemAdapter
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentEditBinding
import com.faris.timetable.viewmodel.EditViewModel
import com.faris.timetable.viewmodelfactory.EditViewModelFactory

class EditFragment : Fragment() {
    private var _binding : FragmentEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val allSubjectDao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = EditViewModelFactory(allSubjectDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditViewModel::class.java)

        binding.editAddButton.setOnClickListener {
            if (binding.editSubjectName.text.toString() != ""){
                viewModel.subjectName = binding.editSubjectName.text.toString()
                viewModel.addSubject()
                binding.editSubjectName.text = null
            }else{
                Toast.makeText(context, getString(R.string.no_subject_name), Toast.LENGTH_SHORT).show()
            }

        }

        val adapter = EditItemAdapter{subjectId ->
            viewModel.deleteSubject(subjectId)
        }
        binding.editRecView.adapter = adapter

        binding.fab.setOnClickListener {
           view.findNavController()
               .navigate(R.id.action_editFragment_to_editDaysFragmentFragment)

        }

        viewModel.allSubjects.observe(viewLifecycleOwner, Observer{
            it.let {
                adapter.submitList(it)
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}