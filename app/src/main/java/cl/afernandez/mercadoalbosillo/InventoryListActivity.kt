package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import cl.afernandez.mercadoalbosillo.adapters.InventarioDetailDialog
import cl.afernandez.mercadoalbosillo.adapters.InventarioListAdapter
import cl.afernandez.mercadoalbosillo.entity.Producto

class InventoryListActivity : AppCompatActivity() {

    private lateinit var listViewInventario: ListView
    private lateinit var adapter: InventarioListAdapter
    private lateinit var producto: MutableList<Producto>
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    companion object {
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_list)

        listViewInventario = findViewById(R.id.listViewProductos)

        producto = mutableListOf(
            Producto("Lechuga", 1090, 4),
            Producto("Berenjena", 1400, 5),
            Producto("Chocolate Vizzio", 2250, 2),
            Producto("Spaghetti", 1229, 10),
            Producto("Yoghurt", 646, 12)
        )

        adapter = InventarioListAdapter(this, R.layout.list_item_inventario, producto)

        listViewInventario.adapter = adapter

        listViewInventario.setOnItemClickListener{_, _, position, _->
            val selectedProducto = producto[position]
            listOption = !listOption
            if (detailOption){
                showInventoryDetailDialog(selectedProducto)
            }
            else{
                val intent = Intent(this, InventoryDetailActivity::class.java)
                intent.putExtra("inventario", selectedProducto)
                startActivity(intent)
            }
        }
    }

    fun goAddProducto(view: View){
        val intent = Intent(this, AddInventoryActivity::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    private fun showInventoryDetailDialog(producto: Producto){
        val dialogo = InventarioDetailDialog(this, producto)
        dialogo.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_REGISTER && resultCode == RESULT_OK){
            val nuevoProducto = data?.getParcelableExtra<Producto>("new")
            if(nuevoProducto != null){
                producto.add(nuevoProducto)
                adapter.notifyDataSetChanged()
            }
        }
    }

}