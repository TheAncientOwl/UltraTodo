package ro.ase.pdm.ultratodo.ui.delete

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoDatabase

class DeleteTodoViewModel(val app: Application) : AndroidViewModel(app) {
    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            TodoDatabase.getInstance(app.applicationContext).getTodoDao()
                .delete(todo)
        }
    }
}