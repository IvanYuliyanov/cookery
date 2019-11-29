package com.example.cookery.ui.mealTypeReceipts

import android.os.Parcel
import android.os.Parcelable

data class ReceiptModel(
    val id              : Long,
    val title           : String,
    val imageUrl        : String,
    val servings        : String,
    val readyInMinutes  : Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(imageUrl)
        parcel.writeString(servings)
        parcel.writeInt(readyInMinutes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReceiptModel> {
        override fun createFromParcel(parcel: Parcel): ReceiptModel {
            return ReceiptModel(parcel)
        }

        override fun newArray(size: Int): Array<ReceiptModel?> {
            return arrayOfNulls(size)
        }
    }

}