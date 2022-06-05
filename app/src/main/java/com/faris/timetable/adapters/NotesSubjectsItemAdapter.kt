package com.faris.timetable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faris.timetable.R
import com.faris.timetable.model.Subject
import com.faris.timetable.util.EditSubjectDiffItemCallback

class NotesSubjectsItemAdapter() :
    ListAdapter<Subject, NotesSubjectsItemAdapter.NotesSubjectItemViewHolder>(EditSubjectDiffItemCallback()) {

    class NotesSubjectItemViewHolder(private val rootView: CardView) :
        RecyclerView.ViewHolder(rootView) {

        val text = rootView.findViewById<TextView>(R.id.note_subject_name)


        companion object {
            fun inflateFrom(parent: ViewGroup): NotesSubjectItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_note_subject, parent, false) as CardView
                return NotesSubjectItemViewHolder(view)
            }
        }

        fun bind(subject: Subject) {
            text.text = subject.subjectName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesSubjectItemViewHolder {
        return NotesSubjectItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: NotesSubjectItemViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(subject)
    }
}