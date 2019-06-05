package org.envirocar.aidl

import android.os.Parcel
import android.os.Parcelable

data class ECMeasurement(
    var latitude: Double,
    var longitude: Double,
    var time: Long,
    var properties: Map<String, String>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readLong(),
        parcel.let {
            val properties = HashMap<String, String>()
            val size = parcel.readInt()
            for (i in 0 until size){
                properties[it.readString()!!] = it.readString()!!
            }
            properties
        }
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeLong(time)

        parcel.writeInt(properties.size)
        for(entry in properties.entries){
            parcel.writeString(entry.key)
            parcel.writeString(entry.value)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ECMeasurement> {
        override fun createFromParcel(parcel: Parcel): ECMeasurement {
            return ECMeasurement(parcel)
        }

        override fun newArray(size: Int): Array<ECMeasurement?> {
            return arrayOfNulls(size)
        }
    }
}