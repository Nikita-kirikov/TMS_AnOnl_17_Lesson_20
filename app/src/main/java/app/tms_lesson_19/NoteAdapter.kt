package app.tms_lesson_19

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

class NoteAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var notes = listOf<NoteType>()

    var onClick : ((Note) -> Unit)? = null
    var onGroupClick : ((Group) -> Unit)? = null
    var onLongClick : ((Note) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        val item = notes[position]
        return when(item) {
            is Note -> {
                if (item.important) {
                    IMPORTANT_TYPE
                } else {
                    UNIMPORTANT_TYPE
                }
            }
            is Group -> GROUP_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            IMPORTANT_TYPE -> NoteImportantViewHolder.from(parent)
            UNIMPORTANT_TYPE -> NoteViewHolder.from(parent)
            GROUP_TYPE -> GroupViewHolder.from(parent)
            else -> throw IllegalStateException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = notes[position]
        if (item is Note && holder is NoteImportantViewHolder) {
            holder.bind(item, onClick, onLongClick)
        } else if (item is Note && holder is NoteViewHolder) {
            holder.bind(item, onClick, onLongClick)
        } else if (item is Group && holder is GroupViewHolder) {
            holder.bind(item, onGroupClick)
        } else {
            throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    companion object {
        private const val IMPORTANT_TYPE = 1
        private const val UNIMPORTANT_TYPE = 2
        private const val GROUP_TYPE = 3
    }
}