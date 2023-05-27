package ro.ase.pdm.ultratodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "TODOs")
class Todo(
    var title: String,
    var priority: TodoPriority,
    var state: TodoState,
    var type: TodoType,
    var description: String,
    var location: Location?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var creationDate = Date()
}
