package cl.afernandez.mercadoalbosillo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Inventario

class InventoryDetailActivity : AppCompatActivity() {

    private lateinit var textName: TextView
    private lateinit var textPrecio: TextView

    private lateinit var inventario: Inventario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_detail)

        textName = findViewById(R.id.textViewProducto)
        textPrecio = findViewById(R.id.textViewPrecio)

        if (Build.VERSION.SDK_INT >= 33){
            inventario = intent.getParcelableExtra("inventario", Inventario::class.java) ?: Inventario("", 0)
        }else{
            inventario = intent.getParcelableExtra("inventario") ?: Inventario("", 0)
        }

        if (inventario != null){
            textName.text = inventario.producto
            textPrecio.setText(inventario.precio.toString())
        }
        /*textName.setOnClickListener(){
            Toast.makeText(this, inventario.precio, Toast.LENGTH_SHORT).show()
        }
         */
    }
}