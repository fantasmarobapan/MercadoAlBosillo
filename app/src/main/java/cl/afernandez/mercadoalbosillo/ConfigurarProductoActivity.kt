package cl.afernandez.mercadoalbosillo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.daos.MovimientoDao
import cl.afernandez.mercadoalbosillo.daos.ProductoDao
import cl.afernandez.mercadoalbosillo.database.AppDataBase
import cl.afernandez.mercadoalbosillo.entity.Movimiento
import cl.afernandez.mercadoalbosillo.entity.Producto

class ConfigurarProductoActivity : AppCompatActivity() {
    private lateinit var db: AppDataBase
    private lateinit var productoDao: ProductoDao
    private lateinit var movimientoDao: MovimientoDao

    private lateinit var editTextNombre: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var editTextMarca: EditText
    private lateinit var editTextCantidad: EditText
    private lateinit var buttonGuardar: Button
    private lateinit var buttonBorrar: Button

    private var nuevoTipoProducto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurar_producto)

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        productoDao = db.productoDao()
        movimientoDao = db.movimientoDao()

        editTextNombre = findViewById(R.id.editTextNombreEdit)
        editTextPrecio = findViewById(R.id.editTextPrecioEdit)
        editTextMarca = findViewById(R.id.editTextMarcaEdit)
        editTextCantidad = findViewById(R.id.editTextCantidadEdit)
        buttonGuardar = findViewById(R.id.saveButton)
        buttonBorrar = findViewById(R.id.deleteButton)

        val buttonBebible = findViewById<Button>(R.id.buttonBebibleEdit)
        val buttonFruta = findViewById<Button>(R.id.buttonFrutaEdit)
        val buttonVerdura = findViewById<Button>(R.id.buttonVerduraEdit)

        buttonBebible.setOnClickListener {
            nuevoTipoProducto = "Bebible"
        }

        buttonFruta.setOnClickListener {
            nuevoTipoProducto = "Fruta"
        }

        buttonVerdura.setOnClickListener {
            nuevoTipoProducto = "Vegetal"
        }

        // Obtener la ID del producto del Intent
        val productoId = intent.getLongExtra("producto_id", -1)

        // Verificar si la ID es válida
        if (productoId != -1L) {
            // Obtener el producto según la ID
            val producto = productoDao.getProductoById(productoId)

            // Verificar si el producto existe
            if (producto != null) {
                // Llenar los EditText con los valores del producto
                editTextNombre.setText(producto.nombre)
                editTextPrecio.setText(producto.precio.toString())
                editTextMarca.setText(producto.marca)
                editTextCantidad.setText(producto.cantidad.toString())
            } else {
                // Manejar el caso en que el producto no existe
                Toast.makeText(
                    applicationContext,
                    "Producto no encontrado",
                    Toast.LENGTH_SHORT
                ).show()
                finish() // Cerrar la actividad si el producto no existe
            }
        } else {
            // Manejar el caso en que la ID no es válida
            Toast.makeText(
                applicationContext,
                "ID de producto no válida",
                Toast.LENGTH_SHORT
            ).show()
            finish() // Cerrar la actividad si la ID no es válida
        }

        // Agregar lógica para los botones (guardar y borrar) según tus necesidades
        buttonGuardar.setOnClickListener {
            // Obtener la ID del producto del Intent
            val productoId = intent.getLongExtra("producto_id", -1)

            // Verificar si la ID es válida
            if (productoId != -1L) {
                // Obtener el producto según la ID
                val producto = productoDao.getProductoById(productoId)

                // Verificar si el producto existe
                if (producto != null) {
                    // Obtener los valores antiguos del producto
                    val nombreAntiguo = producto.nombre
                    val tipoAntiguo = producto.tipo

                    // Actualizar los valores del producto con los ingresados
                    producto.nombre = editTextNombre.text.toString()
                    producto.precio = editTextPrecio.text.toString().toInt()
                    producto.marca = editTextMarca.text.toString()
                    producto.cantidad = editTextCantidad.text.toString().toInt()

                    // Verificar si el tipo de producto ha cambiado
                    if (!nuevoTipoProducto.isNullOrBlank() && nuevoTipoProducto != producto.tipo) {
                        // Actualizar el tipo de producto
                        producto.tipo = nuevoTipoProducto!!

                        // Agregar lógica para registrar el cambio en MovimientoDao
                        val movimiento = Movimiento(
                            itemId = productoId,
                            nombreProducto = producto.nombre ?: "",
                            accion = "Producto actualizado"
                        )
                        movimientoDao.insertMovimiento(movimiento)
                    }

                    // Actualizar el producto en la base de datos
                    productoDao.updateProducto(producto)

                    // Mostrar un mensaje indicando que se guardaron los cambios
                    Toast.makeText(
                        applicationContext,
                        "Cambios guardados",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Cerrar la actividad
                    finish()
                } else {
                    // Manejar el caso en que el producto no existe
                    Toast.makeText(
                        applicationContext,
                        "Producto no encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            } else {
                // Manejar el caso en que la ID no es válida
                Toast.makeText(
                    applicationContext,
                    "ID de producto no válida",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }

        buttonBorrar.setOnClickListener {
            // Obtener la ID del producto del Intent
            val productoId = intent.getLongExtra("producto_id", -1)

            // Verificar si la ID es válida
            if (productoId != -1L) {
                // Obtener el producto según la ID
                val producto = productoDao.getProductoById(productoId)

                // Verificar si el producto existe
                if (producto != null) {
                    // Agregar lógica para registrar el borrado en MovimientoDao
                    val movimiento = Movimiento(
                        itemId = productoId,
                        nombreProducto = producto.nombre ?: "",
                        accion = "Producto eliminado"
                    )
                    movimientoDao.insertMovimiento(movimiento)

                    // Eliminar el producto de la base de datos
                    productoDao.deleteProducto(producto)

                    // Mostrar un mensaje indicando que se eliminó el producto
                    Toast.makeText(
                        applicationContext,
                        "Producto eliminado",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Cerrar la actividad
                    finish()
                } else {
                    // Manejar el caso en que el producto no existe
                    Toast.makeText(
                        applicationContext,
                        "Producto no encontrado",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            } else {
                // Manejar el caso en que la ID no es válida
                Toast.makeText(
                    applicationContext,
                    "ID de producto no válida",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }

    }
}
