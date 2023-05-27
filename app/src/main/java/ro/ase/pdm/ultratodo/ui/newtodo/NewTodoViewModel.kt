package ro.ase.pdm.ultratodo.ui.newtodo

import android.util.Log
import androidx.lifecycle.ViewModel
import ro.ase.pdm.ultratodo.data.Todo

class NewTodoViewModel : ViewModel() {
    fun saveTodo(todo: Todo) {
        // TODO: implement
        Log.d("ULTRA-TODO", "SHOULD SAVE")
        Log.d("ULTRA-TODO", todo.title + " - " + todo.priority.toString() + todo.type.toString())
    }
}