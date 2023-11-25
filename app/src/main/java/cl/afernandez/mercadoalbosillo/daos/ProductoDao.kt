package cl.afernandez.mercadoalbosillo.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.afernandez.mercadoalbosillo.entity.Producto

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProcducto(producto: Producto)

    @Query("SELECT * FROM productos")
    suspend fun getAllProductos(): List<Producto>
}