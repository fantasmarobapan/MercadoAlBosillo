package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.adapters.InventarioListAdapter
import cl.afernandez.mercadoalbosillo.database.AppDataBase
import cl.afernandez.mercadoalbosillo.entity.Producto
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    private lateinit var db: AppDataBase
    private lateinit var inventarioListView: ListView
    private lateinit var inventarioAdapter: InventarioListAdapter
    private lateinit var floatingButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        inventarioListView = findViewById(R.id.inventarioListView)

        // Obtener la lista de productos en lugar de items
        val productos : List<Producto> = db.productoDao().getAllProductos()

        // Crear el adaptador con la lista de productos
        inventarioAdapter = InventarioListAdapter(this, R.layout.item_producto, productos)
        inventarioListView.adapter = inventarioAdapter

        floatingButton = findViewById(R.id.floatingActionButton)

        floatingButton.setOnClickListener {
            startActivity(Intent(this, AddProductoActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.inventarioOpcion -> {
                startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            R.id.movimientoOpcion -> {
                startActivity(Intent(this, MovimientosActivity::class.java))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}

