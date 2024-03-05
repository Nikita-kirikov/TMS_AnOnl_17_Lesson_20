package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.tms_lesson_19.databinding.NoteItemBinding

class NoteViewHolder(private val note : NoteItemBinding) : RecyclerView.ViewHolder(note.root) {

    private val headerView : TextView = note.header
    private val textView : TextView = note.text
    private val dateView : TextView = note.date

    fun bind(note: Note, click: ((Note) -> Unit)?, onLongClick: ((Note) -> Unit)?) {
        headerView.text = note.header
        textView.text = note.text
        dateView.text = note.date

        itemView.setOnClickListener {
            click?.invoke(note)
        }

        itemView.setOnLongClickListener() {
            onLongClick?.invoke(note)
            true
        }
    }

    companion object {
        fun from(parent : ViewGroup) : NoteViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val noteView = NoteItemBinding.inflate(layoutInflater, parent, false)
            return NoteViewHolder(noteView)
        }
    }
}