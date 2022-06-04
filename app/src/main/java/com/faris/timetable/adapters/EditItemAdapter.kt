package com.faris.timetable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faris.timetable.R
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay
import com.faris.timetable.util.EditSubjectDiffItemCallback
import com.faris.timetable.util.SubjectDiffItemCallback

class EditItemAdapter(private val clickListener: (subjectId: Long) -> Unit) :
    ListAdapter<Subject, EditItemAdapter.SubjectItemViewHolder>(EditSubjectDiffItemCallback()) {

    class SubjectItemViewHolder(private val rootView: CardView) :
        RecyclerView.ViewHolder(rootView) {

        val text = rootView.findViewById<TextView>(R.id.edit_subject_name)
        val button = rootView.findViewById<ImageButton>(R.id.edit_delete_button)

        companion object {
            fun inflateFrom(parent: ViewGroup): SubjectItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_subject_with_delete, parent, false) as CardView
                return SubjectItemViewHolder(view)
            }
        }

        fun bind(subject: Subject, clickListener: (subjectId: Long) -> Unit) {
            button.setOnClickListener{
                clickListener(subject.subjectId)
            }
            text.text = subject.subjectName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder {
        return SubjectItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(subject, clickListener)
    }
}