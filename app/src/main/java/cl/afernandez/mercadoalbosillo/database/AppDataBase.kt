package cl.afernandez.mercadoalbosillo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.afernandez.mercadoalbosillo.daos.MovimientoDao
import cl.afernandez.mercadoalbosillo.daos.ProductoDao
import cl.afernandez.mercadoalbosillo.entity.Movimiento
import cl.afernandez.mercadoalbosillo.entity.Producto

@Database(entities = [Producto::class, Movimiento::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun movimientoDao(): MovimientoDao
}

