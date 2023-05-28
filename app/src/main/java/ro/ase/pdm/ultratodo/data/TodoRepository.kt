package ro.ase.pdm.ultratodo.data

import androidx.lifecycle.LiveData

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = todoDao.getAllTodos()

    fun getTodoById(todoId: String): Todo? {
        // Retrieve the todo item from the LiveData
        val todos = allTodos.value
        return todos?.find { it.id.toString() == todoId }
    }

    suspend fun updateTodoItem(todo: Todo) {
        // Implement the update operation using your data source or repository
        // For example, you can call a method in your TodoDao to update the todo item in the database
        todoDao.update(todo)
    }
}

