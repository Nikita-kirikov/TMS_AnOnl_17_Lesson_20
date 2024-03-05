package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.tms_lesson_19.databinding.NoteImportantItemBinding

class NoteImportantViewHolder(private val noteImportant : NoteImportantItemBinding) : RecyclerView.ViewHolder(noteImportant.root) {

    private val headerView : TextView = noteImportant.header
    private val textView : TextView = noteImportant.text
    private val dateView : TextView = noteImportant.date



    fun bind(note : Note, click : ((Note) -> Unit)?, onLongClick: ((Note) -> Unit)?) {
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
        fun from(parent : ViewGroup) : NoteImportantViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val noteView = NoteImportantItemBinding.inflate(layoutInflater, parent, false)
            return NoteImportantViewHolder(noteView)
        }
    }
}