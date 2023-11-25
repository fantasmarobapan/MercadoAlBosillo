package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.adapters.InventarioListAdapter
import cl.afernandez.mercadoalbosillo.database.AppDataBase
import cl.afernandez.mercadoalbosillo.entity.Item

class MainActivity : AppCompatActivity() {
    private lateinit var mToolbar : Toolbar
    //private lateinit var db: AppDataBase
    //private lateinit var inventarioListView: MutableList<Item>
    //private lateinit var inventarioAdapter: InventarioListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        /*db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        inventarioListView = findViewById(R.id.inventarioListView)

        val productos = db.productoDao().getAllProductos()
        val items = db.itemDao().getAllItems()

        val listaInventario = mutableListOf<Item>()
        //listaInventario.addAll(productos)
        listaInventario.addAll(items)*/

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