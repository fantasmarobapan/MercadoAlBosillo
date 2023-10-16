package cl.afernandez.mercadoalbosillo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.entity.Producto

class InventoryDetailActivity : AppCompatActivity() {

    private lateinit var textName: TextView
    private lateinit var textPrecio: TextView

    private lateinit var producto: Producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_detail)

        textName = findViewById(R.id.textViewProducto)
        textPrecio = findViewById(R.id.textViewPrecio)

        if (Build.VERSION.SDK_INT >= 33){
            producto = intent.getParcelableExtra("inventario", Producto::class.java) ?: Producto("", 0)
        }else{
            producto = intent.getParcelableExtra("inventario") ?: Producto("", 0)
        }

        if (producto != null){
            textName.text = producto.producto
            textPrecio.setText(producto.precio.toString())
        }
        /*textName.setOnClickListener(){
            Toast.makeText(this, inventario.precio, Toast.LENGTH_SHORT).show()
        }
         */
    }
}