package ro.ase.pdm.ultratodo.data

import androidx.room.TypeConverter
import java.util.*

class Convertors {
    @TypeConverter
    fun fromTodoType(type: TodoType): String {
        when (type) {
            TodoType.Normal -> {
                return "normal"
            }
            TodoType.Location -> {
                return "location"
            }
            else -> {
                return "normal"
            }
        }
    }

    @TypeConverter
    fun fromStringToType(type: String): TodoType {
        if (type.equals("location")) return TodoType.Location
        return TodoType.Normal
    }

    @TypeConverter
    fun fromLocation(location: Location?): String {
        if (location == null) return "~"

        return location.latitude.toString() + "~" + location.longitude.toString()
    }

    @TypeConverter
    fun fromStringToLocation(location: String): Location? {
        if (location.length == 1) return null

        val coords = location.split("~")

        return Location(coords[0].toDouble(), coords[1].toDouble())
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromLongToDate(data: Long?): Date? {
        return Date(data!!)
    }
}