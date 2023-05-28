package ro.ase.pdm.ultratodo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<Todo>>

    @Query("SELECT * FROM todos WHERE id = :todoId")
    suspend fun getTodoById(todoId: Int): Todo?


    @Insert
    suspend fun insert(todo: Todo)


    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}