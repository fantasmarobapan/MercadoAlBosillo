package cl.afernandez.mercadoalbosillo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Tienda

class TiendaListAdapter(
    context: Context,
    resource: Int,
    tiendas: List<Tienda>
) : ArrayAdapter<Tienda>(context, resource, tiendas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_tienda, null)

        val shop = getItem(position)

        val nombreTextView = listItemView.findViewById<TextView>(R.id.textViewNombre)
        val direccionTextView = listItemView.findViewById<TextView>(R.id.textViewDireccion)
        val valoracionTextView = listItemView.findViewById<TextView>(R.id.textViewValoracion)

        nombreTextView.text = shop?.nombre
        direccionTextView.text = shop?.direccion
        valoracionTextView.text = shop?.valoracion.toString()
        return listItemView
    }
}