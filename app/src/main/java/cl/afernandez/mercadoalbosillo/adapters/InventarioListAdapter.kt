package cl.afernandez.mercadoalbosillo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Item

class InventarioListAdapter(
    context: Context,
    resource: Int,
    items: List<Item>
) : ArrayAdapter<Item>(context, resource, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.item_producto, null)

        val item = getItem(position)

        val imageView = listItemView.findViewById<ImageView>(R.id.imageView)
        val nameTextView = listItemView.findViewById<TextView>(R.id.nameTextView)
        val cantidadTextView = listItemView.findViewById<TextView>(R.id.amountTextView)
        val addButton = listItemView.findViewById<Button>(R.id.plusButton)
        val subtractButton = listItemView.findViewById<Button>(R.id.minusButton)

        // Mostrar imagen según el tipo de producto
        when (item?.producto?.tipo) {
            "Bebible" -> imageView.setImageResource(R.drawable.bebible)
            "Vegetal" -> imageView.setImageResource(R.drawable.fruta)
            "Fruta" -> imageView.setImageResource(R.drawable.vegetal)
        }

        // Actualizar el texto y botones
        nameTextView.text = item?.producto?.nombre
        cantidadTextView.text = item?.cantidad.toString()

        // Puedes agregar lógica para los botones según tus necesidades
        addButton.setOnClickListener {
            // Lógica para aumentar la cantidad
            // Puedes comunicarte con la actividad o fragmento que contiene el adaptador
            // para manejar la lógica de aumento de cantidad
        }

        subtractButton.setOnClickListener {
            // Lógica para disminuir la cantidad
            // Puedes comunicarte con la actividad o fragmento que contiene el adaptador
            // para manejar la lógica de disminución de cantidad
        }

        return listItemView
    }
}
