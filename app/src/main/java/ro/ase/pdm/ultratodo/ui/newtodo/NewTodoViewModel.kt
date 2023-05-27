package ro.ase.pdm.ultratodo.ui.newtodo

import android.util.Log
import androidx.lifecycle.ViewModel
import ro.ase.pdm.ultratodo.data.*

class NewTodoViewModel : ViewModel() {
    fun saveTodo(
        title: String,
        description: String,
        priority: TodoPriority,
        type: TodoType,
        location: Location?
    ) {
        val todo = Todo(
            title,
            priority,
            TodoState.Pending,
            type,
            description,
            location
        )

        // TODO: save it to database
        Log.d("ULTRA-TODO", "SHOULD SAVE")
        Log.d("ULTRA-TODO", todo.title + " - " + todo.priority.toString() + " - " + todo.type.toString())
    }
}