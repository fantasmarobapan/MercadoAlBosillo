package cl.afernandez.mercadoalbosillo.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.afernandez.mercadoalbosillo.entity.Movimiento

@Dao
interface MovimientoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovimiento(movimiento: Movimiento)

    @Query("SELECT * FROM movimientos")
    fun getAllMovimientos(): List<Movimiento>
}