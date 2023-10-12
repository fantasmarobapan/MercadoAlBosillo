package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import cl.afernandez.mercadoalbosillo.adapters.InventarioDetailDialog
import cl.afernandez.mercadoalbosillo.adapters.InventarioListAdapter
import cl.afernandez.mercadoalbosillo.entity.Inventario

class InventoryListActivity : AppCompatActivity() {

    private lateinit var listViewInventario: ListView
    private lateinit var adapter: InventarioListAdapter
    private lateinit var inventario: MutableList<Inventario>
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    companion object {
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory_list)

        listViewInventario = findViewById(R.id.listViewProductos)

        inventario = mutableListOf(
            Inventario("Lechuga", 1090),
            Inventario("Berenjena", 1400),
            Inventario("Chocolate Vizzio", 2250),
            Inventario("Spaghetti", 1229),
            Inventario("Yoghurt", 646)
        )

        adapter = InventarioListAdapter(this, R.layout.list_item_inventario, inventario)

        listViewInventario.adapter = adapter

        listViewInventario.setOnItemClickListener{_, _, position, _->
            val selectedProducto = inventario[position]
            listOption = !listOption
            if (detailOption){
                showInventoryDetailDialog(selectedProducto)
            }
            else{
                val intent = Intent(this, InventarioDetailDialog::class.java)
                intent.putExtra("inventario", selectedProducto)
                startActivity(intent)
            }
        }
    }

    fun goAddProducto(view: View){
        val intent = Intent(this, AddInventoryActivity::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    private fun showInventoryDetailDialog(inventario: Inventario){
        val dialogo = InventarioDetailDialog(this, inventario)
        dialogo.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_REGISTER && resultCode == RESULT_OK){
            val nuevoProducto = data?.getParcelableExtra<Inventario>("new")
            if(nuevoProducto != null){
                inventario.add(nuevoProducto)
                adapter.notifyDataSetChanged()
            }
        }
    }

}