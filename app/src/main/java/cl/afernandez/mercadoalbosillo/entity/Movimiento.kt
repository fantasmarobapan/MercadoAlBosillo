package cl.afernandez.mercadoalbosillo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movimientos")
data class Movimiento(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val itemId: Long,
    val nombreProducto: String,
    val accion: String // Puedes definir acciones como "añadir", "eliminar", etc.
)