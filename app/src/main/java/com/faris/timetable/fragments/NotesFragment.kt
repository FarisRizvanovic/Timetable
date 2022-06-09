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
import androidx.navigation.fragment.findNavController
import com.faris.timetable.adapters.NotesSubjectsItemAdapter
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
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val application = requireNotNull(this.activity).application
        val subjectDao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = NotesViewModelFactory(subjectDao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)

        val adapter = NotesSubjectsItemAdapter{ subjectId, subjectName->
            viewModel.onSubjectClicked(subjectId, subjectName)
        }
        binding.notesRecView.adapter = adapter

        viewModel.allSubjects.observe(viewLifecycleOwner, Observer {
            it?.let{
                adapter.submitList(it)
                if(it.isNotEmpty()){
                    binding.noSubjectsForNotesText.visibility = View.GONE
                    binding.notesRecView.visibility = View.VISIBLE
                }
                if (it.isEmpty()){
                    binding.noSubjectsForNotesText.visibility = View.VISIBLE
                    binding.notesRecView.visibility = View.GONE
                }
            }
        })

        viewModel.navigateToSubjectNotes.observe(viewLifecycleOwner, Observer {
            if (it.first!=null && it.second!=null){
                val action = NotesFragmentDirections
                    .actionNotesFragmentToSubjectNotesFragment(it.first!!, it.second!!)
                this.findNavController().navigate(action)
                viewModel.onSubjectNavigated()
            }
        })



        return view
    }

}