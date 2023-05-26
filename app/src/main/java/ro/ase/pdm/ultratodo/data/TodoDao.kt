package ro.ase.pdm.ultratodo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM TODOs")
    suspend fun getAll(): List<Todo>

    @Insert
    suspend fun insert(todo: Todo): Long
}