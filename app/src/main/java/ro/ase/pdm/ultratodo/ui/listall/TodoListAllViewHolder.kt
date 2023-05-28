package ro.ase.pdm.ultratodo.ui.listall

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.R

class TodoListAllViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    val priorityTextView: TextView = itemView.findViewById(R.id.priorityTextView)
    val stateCheckBox: CheckBox = itemView.findViewById(R.id.stateCheckBox)
    val creationDateTextView: TextView = itemView.findViewById(R.id.creationDateTextView)
}