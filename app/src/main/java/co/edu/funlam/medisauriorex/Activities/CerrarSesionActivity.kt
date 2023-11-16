package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import co.edu.funlam.medisauriorex.R

class CerrarSesionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cerrar_sesion)

        val btnCerrarSesion= this.findViewById<Button>(R.id.btnCerrarSesion)

        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Agregar las banderas para limpiar la pila de actividades
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

        }


    }
}