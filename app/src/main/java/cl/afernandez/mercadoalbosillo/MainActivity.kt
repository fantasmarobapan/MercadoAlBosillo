package cl.afernandez.mercadoalbosillo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goInventory(view: View){
        val intentInventoryList = Intent(this, InventoryListActivity::class.java)
        startActivity(intentInventoryList)
    }
}