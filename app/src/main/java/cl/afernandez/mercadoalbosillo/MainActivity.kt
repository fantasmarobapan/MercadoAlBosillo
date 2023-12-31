package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
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

    private lateinit var searchedittext: EditText
    private lateinit var buttonorder: Button
    private lateinit var ordenesArray: Array<String>
    private var indiceOrdenActual = 0

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

        buttonorder = findViewById(R.id.buttonOrder)

        ordenesArray = arrayOf(
            getString(R.string.ordenDefecto),
            getString(R.string.ordenBebible),
            getString(R.string.ordenFruta),
            getString(R.string.ordenVerdura)
        )
        buttonorder.setOnClickListener {
            // Cambiar al siguiente modo de orden
            indiceOrdenActual = (indiceOrdenActual + 1) % ordenesArray.size

            // Actualizar el texto del botón según el nuevo modo de orden
            buttonorder.text = ordenesArray[indiceOrdenActual]

            // Actualizar la lista después de cambiar el orden
            actualizarLista()
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

    override fun onResume() {
        super.onResume()
        // Actualiza la lista después de cualquier cambio en la base de datos
        actualizarLista()
    }

    fun actualizarLista() {
        // Obtén la nueva lista de productos después de cualquier modificación
        val nuevosProductos: List<Producto> = when (ordenesArray[indiceOrdenActual]) {
            getString(R.string.ordenDefecto) -> db.productoDao().getAllProductos()
            getString(R.string.ordenBebible) -> db.productoDao().getProductosByTipo("Bebible")
            getString(R.string.ordenFruta) -> db.productoDao().getProductosByTipo("Fruta")
            getString(R.string.ordenVerdura) -> db.productoDao().getProductosByTipo("Vegetal")
            else -> db.productoDao().getAllProductos() // Manejo por defecto, si es necesario
        }

        // Limpiar el adaptador y agregar la nueva lista
        inventarioAdapter.clear()
        inventarioAdapter.addAll(nuevosProductos)

        // Notificar al adaptador sobre el cambio
        inventarioAdapter.notifyDataSetChanged()
    }
}

