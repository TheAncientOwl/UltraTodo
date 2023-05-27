package ro.ase.pdm.ultratodo.ui.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.TodoState

class TodoListAdapter(private val todoList: List<Todo>) : RecyclerView.Adapter<TodoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        val currentTodo = todoList[position]

        holder.titleTextView.text = currentTodo.title
        holder.priorityTextView.text = "Priority: ${currentTodo.priority}"
        holder.stateCheckBox.isChecked = currentTodo.state == TodoState.Done;
    }

    override fun getItemCount() = todoList.size
}