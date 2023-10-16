package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable

data class Producto(
    val producto: String?,
    val precio: Int,
    val cantidad: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(producto)
        p0.writeInt(precio)
        p0.writeInt(cantidad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}