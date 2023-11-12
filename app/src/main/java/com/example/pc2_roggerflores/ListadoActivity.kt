package com.example.pc2_roggerflores

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_roggerflores.Adapter.EquipoAdapter
import com.example.pc2_roggerflores.Models.EquiposModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class ListadoActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listadp)
        val rvEquipo = findViewById<RecyclerView>(R.id.rvEquipos)

        val db = FirebaseFirestore.getInstance()
        var listaEquipo: List<EquiposModel>

        db.collection("liga1")
            .addSnapshotListener{snap, e->
                if(e != null){
                    Log.i("ERROR", "Ocurrió un error")
                    return@addSnapshotListener
                }

                listaEquipo = snap!!.documents.map { document ->
                    EquiposModel(
                        document["url"].toString(),
                        document["años fundacion"].toString(),
                        document["nombre equipo"].toString(),
                        document["titulo"].toString()
                    )
                }

                rvEquipo.adapter = EquipoAdapter(listaEquipo)
                rvEquipo.layoutManager = LinearLayoutManager(this)

            }







    }
}