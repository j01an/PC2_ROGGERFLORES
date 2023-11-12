package com.example.pc2_roggerflores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.pc2_roggerflores.Models.EquiposModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtNameEquipo: EditText = findViewById(R.id.txtNameEquipo)
        val txtAnioFundacion: EditText = findViewById(R.id.txtAnioFundacion)
        val txtTituloGanados: EditText = findViewById(R.id.txtTituloGanados)
        val txtUrl: EditText = findViewById(R.id.txtUrl)
        val btnSave: Button = findViewById(R.id.btnSave)

        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("liga1")
        //val auth = FirebaseFirestore.getInstance()

        btnSave.setOnClickListener {
            if(txtNameEquipo.text.isNotEmpty() && txtAnioFundacion.text.isNotEmpty() && txtTituloGanados.text.isNotEmpty() && txtUrl.text.isNotEmpty()){
                val nuevoEquipo = EquiposModel(
                    txtNameEquipo.text.toString(),
                    txtAnioFundacion.text.toString(),
                    txtUrl.text.toString(),
                    txtTituloGanados.text.toString()

                )
                collectionRef.add(nuevoEquipo)
                    .addOnSuccessListener { documentReference ->
                        Snackbar
                            .make(findViewById(android.R.id.content)
                                ,"Registro exitoso ID: ${documentReference.id}"
                                , Snackbar.LENGTH_LONG).show()
                        startActivity(Intent(this,ListadoActivity::class.java))
                    }
                    .addOnFailureListener{ error ->
                        Snackbar
                            .make(findViewById(android.R.id.content)
                                ,"Ocurrió un error: $error"
                                , Snackbar.LENGTH_LONG).show()
                    }
            }else{
                Snackbar.make(findViewById(android.R.id.content),"Por favor completa los campos",
                    Snackbar.LENGTH_LONG).show()
            }


        }



    }
}