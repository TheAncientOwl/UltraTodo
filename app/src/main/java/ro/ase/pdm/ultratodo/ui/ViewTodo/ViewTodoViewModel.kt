package ro.ase.pdm.ultratodo.ui.viewtodo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoDatabase
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoState
import ro.ase.pdm.ultratodo.data.TodoType

class ViewTodoViewModel(app: Application) : AndroidViewModel(app) {
    private val _todo = MutableLiveData<Todo>()
    val todo: LiveData<Todo> get() = _todo

    private val todoDao = TodoDatabase.getInstance(app).getTodoDao()

    fun loadTodoDetails(todoId: Int) {
        viewModelScope.launch {
            val retrievedTodo = todoDao.getTodoById(todoId)
            _todo.value = retrievedTodo ?: Todo("", TodoPriority.Low, TodoState.Pending, TodoType.Normal, "", null)

        }
    }

    fun editTodo() {
        // Handle the edit button click action
        // Navigate to the edit todo page or perform any necessary actions
    }

    fun deleteTodo() {
        // Handle the delete button click action
        // Perform the deletion of the todo item from the database
    }
}
