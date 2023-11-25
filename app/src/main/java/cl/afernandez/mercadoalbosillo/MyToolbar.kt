package cl.afernandez.mercadoalbosillo

import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity

class MyToolbar {
    fun show(activities : AppCompatActivity) {
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
    }
}