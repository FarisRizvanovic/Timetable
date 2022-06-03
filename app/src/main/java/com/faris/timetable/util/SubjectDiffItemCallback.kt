package com.faris.timetable.util

import androidx.recyclerview.widget.DiffUtil
import com.faris.timetable.model.SubjectWithDay

class SubjectDiffItemCallback : DiffUtil.ItemCallback<SubjectWithDay>() {
    override fun areItemsTheSame(oldItem: SubjectWithDay, newItem: SubjectWithDay): Boolean {
        return (oldItem.subjectId == newItem.subjectId)
    }

    override fun areContentsTheSame(oldItem: SubjectWithDay, newItem: SubjectWithDay): Boolean {
        return oldItem == newItem
    }
}