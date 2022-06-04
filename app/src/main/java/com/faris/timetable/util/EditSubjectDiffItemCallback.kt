package com.faris.timetable.util

import androidx.recyclerview.widget.DiffUtil
import com.faris.timetable.model.Subject
import com.faris.timetable.model.SubjectWithDay

class EditSubjectDiffItemCallback: DiffUtil.ItemCallback<Subject>() {
    override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return (oldItem.subjectId == newItem.subjectId)
    }

    override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
        return oldItem == newItem
    }
}