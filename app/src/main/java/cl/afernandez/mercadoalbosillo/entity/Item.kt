package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable

data class Item(
    val producto: Producto,
    val cantidad: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Producto::class.java.classLoader)!!, // Leer el objeto Producto del Parcel
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(producto, flags) // Escribir el objeto Producto en el Parcel
        parcel.writeInt(cantidad)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
