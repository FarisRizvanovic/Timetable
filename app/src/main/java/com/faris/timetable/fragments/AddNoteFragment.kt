package com.faris.timetable.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.faris.timetable.R
import com.faris.timetable.database.SubjectDatabase
import com.faris.timetable.databinding.FragmentAddNoteBinding
import com.faris.timetable.viewmodel.AddNoteViewModel
import com.faris.timetable.viewmodelfactory.AddNoteViewModelFactory

class AddNoteFragment : Fragment() {
    private var _binding : FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val subjectId = AddNoteFragmentArgs.fromBundle(requireArguments()).subjectId

        val application = requireNotNull(this.activity).application
        val dao = SubjectDatabase.getInstance(application).subjectDao
        val viewModelFactory = AddNoteViewModelFactory(subjectId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(AddNoteViewModel::class.java)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.addNoteButton.setOnClickListener {
            if (binding.noteBodyText.text.toString() !=""){
                viewModel.noteBody = binding.noteBodyText.text.toString()
                viewModel.insertNoteForThisSubject()
                findNavController().popBackStack()
                hideKeyboard(this.requireActivity())
            }else{
                Toast.makeText(context, "Molimo uneste text zabiljeske!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    fun hideKeyboard(activity: Activity){
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}