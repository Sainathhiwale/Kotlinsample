package com.examen.kotlinpresmissionn.model

import android.os.Parcel
import android.os.Parcelable

data class Student( val id:Int, val name: String?, val address: String?,  val phone: String?): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(parcel:  Parcel?, p1: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(name)
        parcel?.writeString(address)
        parcel?.writeString(phone)
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}
