package ro.ase.pdm.ultratodo.ui.todolistall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.TodoState

class TodoListAllAdapter(private var todoList: List<Todo>) : RecyclerView.Adapter<TodoListAllViewHolder>() {

    private var onItemClickListener: ((Todo) -> Unit)? = null

    fun setOnItemClickListener(listener: (Todo) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAllViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        val viewHolder = TodoListAllViewHolder(itemView)

        viewHolder.stateCheckBox.isEnabled = false

        return viewHolder
    }

    override fun onBindViewHolder(holder: TodoListAllViewHolder, position: Int) {
        val currentTodo = todoList[position]

        holder.titleTextView.text = currentTodo.title
        holder.priorityTextView.text = "Priority: ${currentTodo.priority}"
        holder.stateCheckBox.isChecked = currentTodo.state == TodoState.Done

        // Set the click listener for the item view
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentTodo)
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateData(newTodoList: List<Todo>) {
        todoList = newTodoList
        notifyDataSetChanged()
    }
}
