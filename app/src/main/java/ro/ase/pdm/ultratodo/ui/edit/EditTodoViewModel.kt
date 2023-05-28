package ro.ase.pdm.ultratodo.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoRepository

class EditTodoViewModel(val app: Application, private val todoRepository: TodoRepository) :
    AndroidViewModel(app) {
    fun updateTodoItem(updatedTodo: Todo) {
        viewModelScope.launch {
            todoRepository.updateTodoItem(updatedTodo)
        }
    }
}