package cl.afernandez.mercadoalbosillo

import android.content.Intent
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
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        val buttonBebible = findViewById<Button>(R.id.buttonBebibleEdit)
        val buttonFruta = findViewById<Button>(R.id.buttonFrutaEdit)
        val buttonVerdura = findViewById<Button>(R.id.buttonVerduraEdit)

        nombreEditText = findViewById(R.id.editTextNombreEdit)
        precioEditText = findViewById(R.id.editTextPrecioEdit)
        marcaEditText = findViewById(R.id.editTextMarcaEdit)
        cantidadEditText = findViewById(R.id.editTextCantidadEdit)

        buttonBebible.setOnClickListener {
            realizarOperacion("Bebible", "Bebible añadido")
        }

        buttonFruta.setOnClickListener {
            realizarOperacion("Fruta", "Fruta añadida")
        }

        buttonVerdura.setOnClickListener {
            realizarOperacion("Vegetal", "Verdura añadida")
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
            itemId = nuevoProducto.id,
            nombreProducto = nuevoProducto.nombre ?: "",
            accion = accion
        )

        db.movimientoDao().insertMovimiento(nuevoMovimiento)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}


