package cl.afernandez.mercadoalbosillo.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.afernandez.mercadoalbosillo.entity.Producto

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducto(vararg producto: Producto)

    @Update
    fun updateProducto(producto: Producto)

    @Delete
    fun deleteProducto(producto: Producto)

    @Query("SELECT * FROM productos")
    fun getAllProductos(): List<Producto>

    @Query("SELECT * FROM productos WHERE id = :productId")
    fun getProductoById(productId: Long): Producto?
}

