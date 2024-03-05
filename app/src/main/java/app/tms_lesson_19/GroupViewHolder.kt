package app.tms_lesson_19

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.tms_lesson_19.databinding.GroupItemBinding

class GroupViewHolder(private val group : GroupItemBinding) : RecyclerView.ViewHolder(group.root) {

    private val groupText : TextView = group.group

    fun bind(group: Group, onGroupClick : ((Group) -> Unit)?) {
        groupText.text = group.text

        itemView.setOnLongClickListener {
            onGroupClick?.invoke(group)
            true
        }
    }

    companion object {
        fun from(parent: ViewGroup) : GroupViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val groupView = GroupItemBinding.inflate(layoutInflater, parent, false)
            return GroupViewHolder(groupView)
        }
    }
}