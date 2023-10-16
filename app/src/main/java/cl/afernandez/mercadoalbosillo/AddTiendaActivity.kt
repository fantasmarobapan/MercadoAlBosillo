package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.afernandez.mercadoalbosillo.entity.Tienda

class AddTiendaActivity : AppCompatActivity() {
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDireccion: EditText
    private lateinit var editTextValoracion: EditText
    private lateinit var buttonSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tienda)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextDireccion = findViewById(R.id.editTextDireccion)
        editTextValoracion = findViewById(R.id.editTextValoracion)
        buttonSave = findViewById(R.id.buttonSaveTienda)

        buttonSave.setOnClickListener{
            val nombre = editTextNombre.text.toString()
            val direccion = editTextDireccion.text.toString()
            val valoracion = editTextValoracion.text.toString().toIntOrNull() ?:0

            val tienda = Tienda(nombre, direccion, valoracion)

            val resultadoIntent = Intent()
            resultadoIntent.putExtra("new", tienda)
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }
    }
}