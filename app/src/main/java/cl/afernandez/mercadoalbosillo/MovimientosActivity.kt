package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.adapters.MovimientoListAdapter
import cl.afernandez.mercadoalbosillo.database.AppDataBase
import cl.afernandez.mercadoalbosillo.entity.Movimiento

class MovimientosActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    private lateinit var db: AppDataBase
    private lateinit var movimientosListView: ListView
    private lateinit var movimientoAdapter: MovimientoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movimientos)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        movimientosListView = findViewById(R.id.movimientosListView)

        // Obtener la lista de movimientos desde la base de datos
        val movimientos: List<Movimiento> = db.movimientoDao().getAllMovimientos()

        // Configurar el adaptador
        movimientoAdapter = MovimientoListAdapter(this, R.layout.movimiento, movimientos)
        movimientosListView.adapter = movimientoAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.inventarioOpcion) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (id == R.id.movimientoOpcion) {
            startActivity(Intent(this, MovimientosActivity::class.java))
        }
        return true
    }
}
