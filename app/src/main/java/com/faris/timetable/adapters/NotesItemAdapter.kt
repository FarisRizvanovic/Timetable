package com.faris.timetable.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faris.timetable.R
import com.faris.timetable.model.Note
import com.faris.timetable.util.NoteDiffItemCallback

class NotesItemAdapter(private val clickListener: (noteId: Long, isChecked : Boolean, deleteClicked : Boolean) -> Unit) :
    ListAdapter<Note, NotesItemAdapter.NoteItemViewHolder>(NoteDiffItemCallback()) {

    class NoteItemViewHolder(private val rootView: CardView) :
        RecyclerView.ViewHolder(rootView) {

        val noteBody = rootView.findViewById<TextView>(R.id.note_body_text)
        val checkBox = rootView.findViewById<CheckBox>(R.id.note_done_check_box)
        val deleteButton = rootView.findViewById<ImageButton>(R.id.note_delete_button)

        companion object {
            fun inflateFrom(parent: ViewGroup): NoteItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_note, parent, false) as CardView
                return NoteItemViewHolder(view)
            }
        }

        fun bind(note: Note,clickListener: (noteId: Long, isChecked : Boolean, deleteClicked : Boolean) -> Unit) {
            checkBox.setOnClickListener {
                    clickListener(note.noteId, checkBox.isChecked, false)
            }
            deleteButton.setOnClickListener {
                clickListener(note.noteId, checkBox.isChecked, true)
            }

            checkBox.isChecked = note.noteDone
            noteBody.text = note.noteBody
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        return NoteItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note,clickListener)
    }
}