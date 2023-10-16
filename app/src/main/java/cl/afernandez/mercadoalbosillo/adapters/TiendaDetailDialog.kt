package cl.afernandez.mercadoalbosillo.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Tienda

class TiendaDetailDialog(
    context: Context,
    private val tienda: Tienda
) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_tienda_detail)

        val textViewName = findViewById<TextView>(R.id.textViewNombre)
        val textViewPrecio = findViewById<TextView>(R.id.textViewDireccion)
        val textViewValoracion = findViewById<TextView>(R.id.textViewValoracion)
        val buttonGoBack = findViewById<Button>(R.id.buttonGoBackDialog)

        textViewName.text = tienda.nombre
        textViewPrecio.text = tienda.direccion
        textViewValoracion.text = tienda.valoracion.toString()

        buttonGoBack.setOnClickListener{
            dismiss()
        }
    }
}