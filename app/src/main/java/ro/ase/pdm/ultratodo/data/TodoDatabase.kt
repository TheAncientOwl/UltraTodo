package ro.ase.pdm.ultratodo.data

import android.content.Context
import androidx.room.*

@Database(entities = arrayOf(Todo::class), version = 1)
@TypeConverters(Convertors::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoDao(): TodoDao

    // Volatile, Synchronized

    companion object {
        private var todoDatabase: TodoDatabase? = null

        fun getInstance(context: Context): TodoDatabase {
            if (todoDatabase == null) {
                todoDatabase = Room
                    .databaseBuilder(
                        context,
                        TodoDatabase::class.java,
                        "todo_data.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return todoDatabase!!
        }
    }
}