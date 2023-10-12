package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable

data class Inventario(
    val producto: String?,
    val precio: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(producto)
        p0.writeInt(precio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Inventario> {
        override fun createFromParcel(parcel: Parcel): Inventario {
            return Inventario(parcel)
        }

        override fun newArray(size: Int): Array<Inventario?> {
            return arrayOfNulls(size)
        }
    }
}