package cl.afernandez.mercadoalbosillo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.afernandez.mercadoalbosillo.entity.Producto

@Database(entities = [Producto::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun productoDao(): Producto
}