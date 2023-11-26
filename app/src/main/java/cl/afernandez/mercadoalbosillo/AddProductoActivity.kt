package cl.afernandez.mercadoalbosillo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.database.AppDataBase
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

        val buttonBebible = findViewById<Button>(R.id.buttonBebible)
        val buttonFruta = findViewById<Button>(R.id.buttonFruta)
        val buttonVerdura = findViewById<Button>(R.id.buttonVerdura)

        nombreEditText = findViewById(R.id.editTextNombre)
        precioEditText = findViewById(R.id.editTextPrecio)
        marcaEditText = findViewById(R.id.editTextMarca)
        cantidadEditText = findViewById(R.id.editTextCantidad)

        buttonBebible.setOnClickListener {
            realizarOperacion("Bebible", "Bebible añadido")
        }

        buttonFruta.setOnClickListener {
            realizarOperacion("Fruta", "Fruta añadida")
        }

        buttonVerdura.setOnClickListener {
            realizarOperacion("Verdura", "Verdura añadida")
        }
    }

    private fun realizarOperacion(tipo: String, accion: String) {
        val nombre = nombreEditText.text.toString()
        val precio = precioEditText.text.toString().toIntOrNull() ?: 0
        val marca = marcaEditText.text.toString()
        val cantidad = cantidadEditText.text.toString().toIntOrNull() ?: 0

        val nuevoProducto = Producto(
            nombre = nombre,
            precio = precio,
            marca = marca,
            tipo = tipo,
            cantidad = cantidad
        )

        db.productoDao().insertProducto(nuevoProducto)

        val nuevoMovimiento = Movimiento(
            itemId = nuevoProducto.id, // No estoy seguro de dónde obtienes esto
            nombreProducto = nuevoProducto.nombre ?: "",
            accion = accion
        )

        db.movimientoDao().insertMovimiento(nuevoMovimiento)

        finish()
    }
}
