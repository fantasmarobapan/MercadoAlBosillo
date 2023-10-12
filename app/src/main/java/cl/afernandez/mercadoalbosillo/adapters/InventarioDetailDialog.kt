package cl.afernandez.mercadoalbosillo.adapters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Inventario

class InventarioDetailDialog(
    context: Context,
    private val inventario: Inventario
) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_inventario_detail)

        val textViewName = findViewById<TextView>(R.id.textViewProducto)
        val textViewPrecio = findViewById<TextView>(R.id.textViewPrecio)
        val buttonGoBack = findViewById<Button>(R.id.buttonGoBackDialog)

        textViewName.text = inventario.producto
        textViewPrecio.text = inventario.precio.toString()

        buttonGoBack.setOnClickListener{
            dismiss()
        }
    }
}