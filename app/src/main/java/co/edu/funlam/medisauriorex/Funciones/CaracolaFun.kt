package co.edu.funlam.medisauriorex.Funciones

import android.content.Context
import android.content.Intent
import co.edu.funlam.medisauriorex.Activities.CerrarSesionActivity
import co.edu.funlam.medisauriorex.Activities.ListaMascotasActivity

class CaracolaFun {

    companion object{
        fun caracolaFunction(contex : Context){
            val intent = Intent(contex, CerrarSesionActivity::class.java)
            contex.startActivity(intent)
        }
    }
}