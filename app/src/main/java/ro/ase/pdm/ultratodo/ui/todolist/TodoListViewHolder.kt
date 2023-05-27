package ro.ase.pdm.ultratodo.ui.todolist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.R

class TodoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    val priorityTextView: TextView = itemView.findViewById(R.id.priorityTextView)
    val stateTextView: TextView = itemView.findViewById(R.id.stateTextView)
}