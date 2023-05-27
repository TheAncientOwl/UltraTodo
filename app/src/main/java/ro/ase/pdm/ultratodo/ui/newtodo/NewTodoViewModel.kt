package ro.ase.pdm.ultratodo.ui.newtodo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.data.*

class NewTodoViewModel(val app: Application) : AndroidViewModel(app) {
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

        viewModelScope.launch {
            TodoDatabase.getInstance(app.applicationContext).getTodoDao()
                .insert(todo)
        }

        Log.d("ULTRA-TODO", "SHOULD SAVE")
        Log.d(
            "ULTRA-TODO",
            todo.title + " - " + todo.priority.toString() + " - " + todo.type.toString()
        )
    }
}