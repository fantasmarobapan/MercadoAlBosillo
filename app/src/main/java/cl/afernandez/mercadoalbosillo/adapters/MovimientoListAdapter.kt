package cl.afernandez.mercadoalbosillo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Movimiento

class MovimientoListAdapter(
    context: Context,
    resource: Int,
    movimientos: List<Movimiento>
) : ArrayAdapter<Movimiento>(context, resource, movimientos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.movimiento, null)

        val movimiento = getItem(position)

        val nombreProductoTextView = listItemView.findViewById<TextView>(R.id.textViewMovimiento)
        val accionTextView = listItemView.findViewById<TextView>(R.id.accionTextView)

        // Actualizar el texto
        nombreProductoTextView.text = movimiento?.nombreProducto
        accionTextView.text = movimiento?.accion

        return listItemView
    }
}