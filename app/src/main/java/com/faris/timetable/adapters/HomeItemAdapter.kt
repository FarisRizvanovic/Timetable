package com.faris.timetable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faris.timetable.R
import com.faris.timetable.model.SubjectWithDay
import com.faris.timetable.util.SubjectDiffItemCallback

class HomeItemAdapter :
    ListAdapter<SubjectWithDay, HomeItemAdapter.SubjectItemViewHolder>(SubjectDiffItemCallback()) {

    class SubjectItemViewHolder(private val rootView: CardView) :
        RecyclerView.ViewHolder(rootView) {

        val text = rootView.findViewById<TextView>(R.id.home_subject_name)

        companion object {
            fun inflateFrom(parent: ViewGroup): SubjectItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_subject, parent, false) as CardView
                return SubjectItemViewHolder(view)
            }
        }

        fun bind(subjectWithDay: SubjectWithDay) {
                text.text = subjectWithDay.subjectName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder {
        return SubjectItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val subject = getItem(position)
        holder.bind(subject)
    }
}