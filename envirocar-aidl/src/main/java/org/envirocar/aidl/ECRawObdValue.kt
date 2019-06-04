package org.envirocar.aidl

import android.os.Parcel
import android.os.Parcelable

data class ECRawObdValue(
    val value: Double,
    val unit: String,
    val time: Long
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(value)
        parcel.writeString(unit)
        parcel.writeLong(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ECRawObdValue> {
        override fun createFromParcel(parcel: Parcel): ECRawObdValue {
            return ECRawObdValue(parcel)
        }

        override fun newArray(size: Int): Array<ECRawObdValue?> {
            return arrayOfNulls(size)
        }
    }
}