package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.R
import com.faris.timetable.database.AllSubjectDatabase
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
        _binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val allSubjectDao = AllSubjectDatabase.getInstance(application).allSubjectDao
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

        binding.fab.setOnClickListener {
            val test = viewModel.allSubjects.value!!
            Toast.makeText(context, test[0].subjectName, Toast.LENGTH_SHORT).show()
        }

        viewModel.allSubjects.observe(viewLifecycleOwner, Observer{
            Toast.makeText(context, it[0].subjectName, Toast.LENGTH_SHORT).show()
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}