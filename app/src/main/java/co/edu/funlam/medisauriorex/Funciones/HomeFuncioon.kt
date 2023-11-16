package co.edu.funlam.medisauriorex.Funciones

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import co.edu.funlam.medisauriorex.Activities.ListaMascotasActivity
import co.edu.funlam.medisauriorex.Activities.RecordatorioDino

class HomeFuncioon {
    companion object{
        fun homeFunction(contex : Context, correoDueño: String){
            val intent = Intent(contex, ListaMascotasActivity::class.java)
            // Agregar las banderas para limpiar la pila de actividades
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            intent.putExtra("emailDueño", correoDueño)

            contex.startActivity(intent)
        }
    }
}