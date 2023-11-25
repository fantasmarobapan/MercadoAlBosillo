package cl.afernandez.mercadoalbosillo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.database.AppDataBase
import cl.afernandez.mercadoalbosillo.entity.Item
import cl.afernandez.mercadoalbosillo.entity.Movimiento
import cl.afernandez.mercadoalbosillo.entity.Producto


class AddProductoActivity : AppCompatActivity() {

    private lateinit var db: AppDataBase
    private lateinit var nombreEditText: EditText
    private lateinit var precioEditText: EditText
    private lateinit var marcaEditText: EditText
    private lateinit var cantidadEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_producto)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-mine"
        ).allowMainThreadQueries().build()

        val ButtonBebible = findViewById<Button>(R.id.buttonBebible)
        val ButtonFruta = findViewById<Button>(R.id.buttonFruta)
        val ButtonVerdura = findViewById<Button>(R.id.buttonVerdura)

        nombreEditText = findViewById(R.id.editTextNombre)
        precioEditText = findViewById(R.id.editTextPrecio)
        marcaEditText = findViewById(R.id.editTextMarca)
        cantidadEditText = findViewById(R.id.editTextCantidad)

        ButtonBebible.setOnClickListener {
            realizarOperacion("Bebible", "Bebible añadido")
        }

        ButtonFruta.setOnClickListener {
            realizarOperacion("Fruta", "Fruta añadida")
        }

        ButtonVerdura.setOnClickListener {
            realizarOperacion("Verdura", "Verdura añadida")
        }
    }

    private fun realizarOperacion(tipo: String, accion: String) {
        val Snombre = nombreEditText.text.toString()
        val Sprecio = precioEditText.text.toString().toIntOrNull() ?: 0
        val Smarca = marcaEditText.text.toString()
        val Scantidad = cantidadEditText.text.toString().toIntOrNull() ?: 0

        val nuevoproducto = Producto(
            nombre = Snombre,
            precio = Sprecio,
            marca = Smarca,
            tipo = tipo
        )

        db.productoDao().insertProducto(nuevoproducto)

        val nuevoItem = Item(
            producto = nuevoproducto,
            cantidad = Scantidad
        )
        db.itemDao().insertItem(nuevoItem)

        val nuevoMovimiento = Movimiento(
            itemId = nuevoItem.id,
            nombreProducto = nuevoproducto.nombre ?: "",
            accion = accion
        )

        db.movimientoDao().insertMovimiento(nuevoMovimiento)

        finish()
    }
}