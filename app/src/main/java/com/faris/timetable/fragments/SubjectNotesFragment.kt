package com.faris.timetable.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.faris.timetable.MainActivity
import com.faris.timetable.R
import com.faris.timetable.adapters.NotesItemAdapter
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentSubjectNotesBinding
import com.faris.timetable.viewmodel.SubjectNoteViewModel
import com.faris.timetable.viewmodelfactory.SubjectNoteViewModelFactory


class SubjectNotesFragment : Fragment() {
    private var _binding : FragmentSubjectNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubjectNotesBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


        val subjectId = SubjectNotesFragmentArgs.fromBundle(requireArguments()).subjectId
        val subjectName = SubjectNotesFragmentArgs.fromBundle(requireArguments()).subjectName

        binding.noteHeadline.text = "$subjectName:"

        val application = requireNotNull(this.activity).application
        val dao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = SubjectNoteViewModelFactory(subjectId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(SubjectNoteViewModel::class.java)

        val adapter = NotesItemAdapter{noteId, isChecked, deleteClicked ->
            if (!deleteClicked){
                viewModel.isDone = isChecked
                viewModel.shouldDelete = false
                viewModel.getTask(noteId)
            }else{
                viewModel.shouldDelete = true
                viewModel.getTask(noteId)
            }

        }
        binding.subjectNotesRecView.adapter = adapter

        viewModel.notesForThisSubject.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.fabAdd.setOnClickListener {
            val action = SubjectNotesFragmentDirections.actionSubjectNotesFragmentToAddNoteFragment(subjectId)
            view.findNavController().navigate(action)
        }

        binding.fabLeft.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.note.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (!viewModel.shouldDelete){
                    viewModel.updateTask()
                }else{
                    viewModel.deleteSubject()
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