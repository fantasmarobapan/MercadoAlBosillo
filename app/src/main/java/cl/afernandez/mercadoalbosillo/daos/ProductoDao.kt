package cl.afernandez.mercadoalbosillo.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.afernandez.mercadoalbosillo.entity.Producto

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducto(vararg producto: Producto)

    @Query("SELECT * FROM productos")
    fun getAllProductos(): List<Producto>
}