package ro.ase.pdm.ultratodo.data

import android.os.Parcel
import android.os.Parcelable

enum class TodoState : Parcelable {
    Pending,
    Done;

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TodoState> {
        override fun createFromParcel(parcel: Parcel): TodoState {
            return values()[parcel.readInt()]
        }

        override fun newArray(size: Int): Array<TodoState?> {
            return arrayOfNulls(size)
        }
    }
}