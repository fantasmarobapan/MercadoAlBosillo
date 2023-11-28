package cl.afernandez.mercadoalbosillo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import cl.afernandez.mercadoalbosillo.ConfigurarProductoActivity
import cl.afernandez.mercadoalbosillo.R
import cl.afernandez.mercadoalbosillo.entity.Producto

class InventarioListAdapter(
    context: Context,
    resource: Int,
    productos: List<Producto>
) : ArrayAdapter<Producto>(context, resource, productos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.item_producto, null)

        val producto = getItem(position)

        val imageView = listItemView.findViewById<ImageView>(R.id.imageView)
        val nameTextView = listItemView.findViewById<TextView>(R.id.nameTextView)
        val cantidadTextView = listItemView.findViewById<TextView>(R.id.amountTextView)
        val editButton = listItemView.findViewById<Button>(R.id.editButton)

        // Mostrar imagen según el tipo de producto
        when (producto?.tipo) {
            "Bebible" -> imageView.setImageResource(R.drawable.bebible)
            "Vegetal" -> imageView.setImageResource(R.drawable.vegetal)
            "Fruta" -> imageView.setImageResource(R.drawable.fruta)
        }

        // Actualizar el texto y botones
        nameTextView.text = producto?.nombre
        cantidadTextView.text = producto?.cantidad.toString()

        // Configura el botón para abrir ConfigurarProductoActivity con el producto correspondiente
        editButton.setOnClickListener {
            val intent = Intent(context, ConfigurarProductoActivity::class.java)
            intent.putExtra("producto_id", producto?.id)
            context.startActivity(intent)
        }

        return listItemView
    }
}