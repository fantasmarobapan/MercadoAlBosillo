package cl.afernandez.mercadoalbosillo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.entity.Producto
import cl.afernandez.mercadoalbosillo.entity.Tienda

class TiendaDetailActivity : AppCompatActivity() {

    private lateinit var textNombre: TextView
    private lateinit var textDireccion: TextView
    private lateinit var textValoracion: TextView

    private lateinit var tienda: Tienda
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda_detail)

        textNombre = findViewById(R.id.textViewNombre)
        textDireccion = findViewById(R.id.textViewDireccion)
        textValoracion = findViewById(R.id.textViewValoracion)

        if (Build.VERSION.SDK_INT >= 33){
            tienda = intent.getParcelableExtra("tienda", Tienda::class.java) ?: Tienda("", "", 0)
        }else{
            tienda = intent.getParcelableExtra("tienda") ?: Tienda("", "",0)
        }

        if (tienda != null){
            textNombre.text = tienda.nombre
            textDireccion.text = tienda.direccion
            textValoracion.setText(tienda.valoracion.toString())
        }
    }
}