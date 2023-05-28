package ro.ase.pdm.ultratodo.data

import android.os.Parcel
import android.os.Parcelable

enum class TodoType : Parcelable {
    Normal,
    Location;

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoType> {
        override fun createFromParcel(parcel: Parcel): TodoType {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<TodoType?> {
            return arrayOfNulls(size)
        }
    }
}