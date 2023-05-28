package ro.ase.pdm.ultratodo.ui.EditTodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoRepository

class EditTodoViewModel(val app: Application, private val todoRepository: TodoRepository) : AndroidViewModel(app) {
    private var currentTodo: Todo? = null

    fun setCurrentTodo(todo: Todo) {
        currentTodo = todo
    }

    fun updateTodoItem2(updatedTodo: Todo) {
        currentTodo?.let { todo ->
            todo.title = updatedTodo.title
            todo.priority = updatedTodo.priority
            todo.state = updatedTodo.state
            todo.description = updatedTodo.description
            viewModelScope.launch {
                todoRepository.updateTodoItem(todo)
            }
        }
    }
}