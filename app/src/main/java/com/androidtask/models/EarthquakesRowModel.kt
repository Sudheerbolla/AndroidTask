package com.androidtask.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class EarthquakesRowModel(
    @SerializedName("datetime") var datetime: String? = null,
    @SerializedName("depth") var depth: Double? = null,
    @SerializedName("lng") var lng: Double? = null,
    @SerializedName("src") var src: String? = null,
    @SerializedName("eqid") var eqid: String? = null,
    @SerializedName("magnitude") var magnitude: Double? = null,
    @SerializedName("lat") var lat: Double? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(datetime)
        parcel.writeValue(depth)
        parcel.writeValue(lng)
        parcel.writeString(src)
        parcel.writeString(eqid)
        parcel.writeValue(magnitude)
        parcel.writeValue(lat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EarthquakesRowModel> {
        override fun createFromParcel(parcel: Parcel): EarthquakesRowModel {
            return EarthquakesRowModel(parcel)
        }

        override fun newArray(size: Int): Array<EarthquakesRowModel?> {
            return arrayOfNulls(size)
        }
    }
}