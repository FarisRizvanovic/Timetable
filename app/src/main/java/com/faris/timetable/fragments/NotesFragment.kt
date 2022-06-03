package com.faris.timetable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentNotesBinding
import com.faris.timetable.viewmodel.NotesViewModel
import com.faris.timetable.viewmodelfactory.NotesViewModelFactory

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val subjectDao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = NotesViewModelFactory(subjectDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)


        binding.addButton.setOnClickListener {
            if (binding.subjectName.text.toString() != "") {
                viewModel.newSubjectName = binding.subjectName.text.toString()
                viewModel.newDayId = binding.dayId.text.toString().toInt()
                viewModel.addSubject()
                Toast.makeText(context, "Subject added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Task name blank!", Toast.LENGTH_SHORT).show()
            }
        }



        return view
    }

}