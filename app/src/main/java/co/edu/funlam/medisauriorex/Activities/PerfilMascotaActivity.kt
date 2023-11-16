package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Funciones.CaracolaFun
import co.edu.funlam.medisauriorex.Funciones.HomeFuncioon
import co.edu.funlam.medisauriorex.Funciones.TipoDino
import co.edu.funlam.medisauriorex.R

class PerfilMascotaActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mascota_perfil_layout)

        val imageViewPerfilMascota = this.findViewById<ImageView>(R.id.imageView_mascotPerfil)
        val txtNombreMascotaPerfil= this.findViewById<TextView>(R.id.textView_PerfilNombreMascota)
        val btnHistorialClinico = this.findViewById<Button>(R.id.button_mascotaPerfil_HistorialClinico)
        val btnRecordatorio = this.findViewById<Button>(R.id.button_mascotaPerfil_Recordatorio)
        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomePerfilMascotas)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionPerfilMascotas)

        val emailDueño = intent.getStringExtra("emailDueño") as String

        imgHome.setOnClickListener {
            HomeFuncioon.homeFunction(this, emailDueño)
        }

        imgCaracola.setOnClickListener {
           CaracolaFun.caracolaFunction(this)
        }




        val mascota = intent.getSerializableExtra("mascota") as Mascota

        txtNombreMascotaPerfil.text= mascota.nombreDino


        val imagenDino= TipoDino.getDrawableResource(mascota.tipoMascota)

        imageViewPerfilMascota.setImageResource(imagenDino)

        btnHistorialClinico.setOnClickListener {

            val intent = Intent(this, HistoriaClinicaActivity::class.java)
            intent.putExtra("mascota", mascota)
            intent.putExtra("emailDueño", emailDueño)
            startActivity(intent)


        }

        btnRecordatorio.setOnClickListener {

            val intent = Intent(this, RecordatorioDino::class.java)
            intent.putExtra("mascota", mascota)
            startActivity(intent)


        }



    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}