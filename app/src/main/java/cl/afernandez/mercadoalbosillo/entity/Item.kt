package cl.afernandez.mercadoalbosillo.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @Embedded val producto: Producto,
    val cantidad: Int
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Producto::class.java.classLoader)!!, // Leer el objeto Producto del Parcel
        parcel.readInt(),
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

    fun toMovimiento(accion: String): Movimiento {
        return Movimiento(
            itemId = id,
            nombreProducto = producto.nombre ?: "",
            accion = accion
        )
    }
}
