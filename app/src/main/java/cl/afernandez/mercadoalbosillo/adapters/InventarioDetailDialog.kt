package cl.afernandez.mercadoalbosillo.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Producto

class InventarioDetailDialog(
    context: Context,
    private val producto: Producto
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_inventario_detail)

        val textViewName = findViewById<TextView>(R.id.textViewProducto)
        val textViewPrecio = findViewById<TextView>(R.id.textViewPrecio)
        val textViewCantidad = findViewById<TextView>(R.id.textViewCantidad)
        val buttonGoBack = findViewById<Button>(R.id.buttonGoBackDialog)

        textViewName.text = producto.producto
        textViewPrecio.text = producto.precio.toString()
        textViewCantidad.text = producto.precio.toString()

        buttonGoBack.setOnClickListener{
            dismiss()
        }
    }
}