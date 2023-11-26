package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String?,
    val precio: Int,
    val marca: String?,
    val tipo: String?,
    val cantidad: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeLong(id)
        p0.writeString(nombre)
        p0.writeInt(precio)
        p0.writeString(marca)
        p0.writeString(tipo)
        p0.writeInt(cantidad) // Escribir la nueva propiedad cantidad
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
