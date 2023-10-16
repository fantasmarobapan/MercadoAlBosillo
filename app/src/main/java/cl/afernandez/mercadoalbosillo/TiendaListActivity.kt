package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import cl.afernandez.mercadoalbosillo.adapters.TiendaDetailDialog
import cl.afernandez.mercadoalbosillo.adapters.TiendaListAdapter
import cl.afernandez.mercadoalbosillo.entity.Tienda

class TiendaListActivity : AppCompatActivity() {
    private lateinit var listViewTienda: ListView
    private lateinit var adapter: TiendaListAdapter
    private lateinit var tienda: MutableList<Tienda>
    private var listOption: Boolean = true
    private var detailOption: Boolean = false
    companion object {
        const val REQUEST_REGISTER = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda_list)

        listViewTienda = findViewById(R.id.listViewTiendas)

        tienda = mutableListOf(
            Tienda("Nombre1", "Calle N numero X", 3),
            Tienda("Los cariocas", "Calle alegria por salida Oriente", 4),
            Tienda("Donde Pepe", "Avenida Allende 2030", 5)
        )

        adapter = TiendaListAdapter(this, R.layout.list_item_tienda, tienda)

        listViewTienda.adapter = adapter

        listViewTienda.setOnItemClickListener{ _, _, position, _->
            val selectedTienda = tienda[position]
            listOption = !listOption
            if (detailOption){
                showTiendaDetailDialog(selectedTienda)
            }
            else{
                val intent = Intent(this, TiendaDetailActivity::class.java)
                intent.putExtra("tienda", selectedTienda)
                startActivity(intent)
            }
        }
    }

    fun goAddTienda(view: View){
        val intent = Intent(this, AddTiendaActivity::class.java)
        startActivityForResult(intent, REQUEST_REGISTER)
    }

    private fun showTiendaDetailDialog(tienda: Tienda){
        val dialogo = TiendaDetailDialog(this, tienda)
        dialogo.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_REGISTER && resultCode == RESULT_OK){
            val nuevoTienda = data?.getParcelableExtra<Tienda>("new")
            if(nuevoTienda != null){
                tienda.add(nuevoTienda)
                adapter.notifyDataSetChanged()
            }
        }
    }
}