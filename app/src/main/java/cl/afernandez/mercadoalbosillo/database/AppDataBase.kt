package cl.afernandez.mercadoalbosillo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.afernandez.mercadoalbosillo.daos.ItemDao
import cl.afernandez.mercadoalbosillo.daos.MovimientoDao
import cl.afernandez.mercadoalbosillo.daos.ProductoDao
import cl.afernandez.mercadoalbosillo.entity.Producto

@Database(entities = [Producto::class, ItemDao::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun itemDao(): ItemDao
    abstract fun movimientoDao(): MovimientoDao
}