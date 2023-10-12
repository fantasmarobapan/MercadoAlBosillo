package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import cl.afernandez.mercadoalbosillo.entity.Inventario

class AddInventoryActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_inventory)

        editTextName = findViewById(R.id.editTextName)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val nombre = editTextName.text.toString()
            val precio = editTextPrecio.text.toString().toIntOrNull() ?: 0

            val inventario = Inventario(nombre, precio)

            val resultadoIntent = Intent()
            resultadoIntent.putExtra("new", inventario)
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }
    }
}