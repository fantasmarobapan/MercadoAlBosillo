package cl.afernandez.mercadoalbosillo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Inventario

class InventarioListAdapter(
    context: Context,
    resource: Int,
    inventarios: List<Inventario>
) : ArrayAdapter<Inventario>(context, resource, inventarios){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView =convertView ?: inflater.inflate(R.layout.list_item_inventario, null)

        val inventory= getItem(position)

        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewProducto)
        val precioTextView = listItemView.findViewById<TextView>(R.id.textViewPrecio)

        nameTextView.text = inventory?.producto
        precioTextView.text = inventory?.precio.toString()

        return listItemView
    }
}