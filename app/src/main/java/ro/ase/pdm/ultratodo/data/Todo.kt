package ro.ase.pdm.ultratodo.data

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "TODOs")
data class Todo(
    var title: String,
    var priority: TodoPriority,
    var state: TodoState,
    var type: TodoType,
    var description: String,
    var location: Location?
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var creationDate: Date = Date()

    // ...


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeParcelable(priority, flags)
        dest.writeParcelable(state, flags)
        dest.writeParcelable(type, flags)
        dest.writeString(description)
        dest.writeParcelable(location, flags)
        dest.writeLong(id)
        dest.writeLong(creationDate.time)
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(
                parcel.readString()!!,
                parcel.readParcelable(TodoPriority::class.java.classLoader)!!,
                parcel.readParcelable(TodoState::class.java.classLoader)!!,
                parcel.readParcelable(TodoType::class.java.classLoader)!!,
                parcel.readString()!!,
                parcel.readParcelable(Location::class.java.classLoader),
            ).apply {
                id = parcel.readLong()
                creationDate = Date(parcel.readLong())
            }
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}


