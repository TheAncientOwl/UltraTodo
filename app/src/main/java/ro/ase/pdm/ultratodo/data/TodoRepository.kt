package ro.ase.pdm.ultratodo.data

import androidx.lifecycle.LiveData

class TodoRepository(todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()
}
