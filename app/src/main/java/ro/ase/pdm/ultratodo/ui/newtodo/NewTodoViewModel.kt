package ro.ase.pdm.ultratodo.ui.newtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoState
import ro.ase.pdm.ultratodo.data.TodoType
import java.util.Date

class NewTodoViewModel : ViewModel() {
    private val _newTodo = MutableLiveData<Todo>().apply {
        val todo = this.value!!

        todo.name = "New TODO"
        todo.priority = TodoPriority.Low
        todo.state = TodoState.Pending
        todo.type = TodoType.Normal
        todo.description = "new description"
        todo.location = null
        todo.creationDate = Date()
    }

    val todo: LiveData<Todo> = _newTodo
}