package com.faris.timetable.util

import android.provider.ContactsContract
import androidx.recyclerview.widget.DiffUtil
import com.faris.timetable.model.Note

class NoteDiffItemCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return (oldItem.noteId == newItem.noteId)
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}