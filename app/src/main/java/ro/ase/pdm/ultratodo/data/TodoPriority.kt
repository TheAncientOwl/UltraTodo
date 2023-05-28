package ro.ase.pdm.ultratodo.data

import android.os.Parcel
import android.os.Parcelable

enum class TodoPriority : Parcelable {
    Urgent,
    High,
    Medium,
    Low;

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoPriority> {
        override fun createFromParcel(parcel: Parcel): TodoPriority {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<TodoPriority?> {
            return arrayOfNulls(size)
        }
    }
}