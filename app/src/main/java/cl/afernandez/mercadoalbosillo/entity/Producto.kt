package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable

data class Producto(
    val producto: String?,
    val precio: Int,
    val marca: String?,
    val tipo: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(producto)
        p0.writeInt(precio)
        p0.writeString(marca)
        p0.writeString(tipo)
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