package ro.ase.pdm.ultratodo.ui.todolistall

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoRepository

class TodoListAllViewModel(app: Application) : AndroidViewModel(app) {
    private lateinit var todoRepository: TodoRepository
    var allTodos: LiveData<List<Todo>> = MutableLiveData()

    fun setTodoRepository(repository: TodoRepository) {
        todoRepository = repository
        allTodos = todoRepository.allTodos
    }
}