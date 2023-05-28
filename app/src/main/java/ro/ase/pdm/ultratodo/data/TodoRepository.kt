package ro.ase.pdm.ultratodo.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    fun getTodoById(todoId: String): Todo? {
        val todos = allTodos.value
        return todos?.find { it.id.toString() == todoId }
    }

    suspend fun updateTodoItem(todo: Todo) {
        todoDao.update(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }
}