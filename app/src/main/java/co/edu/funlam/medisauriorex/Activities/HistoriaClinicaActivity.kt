package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Funciones.CaracolaFun
import co.edu.funlam.medisauriorex.Funciones.HomeFuncioon
import co.edu.funlam.medisauriorex.Funciones.TipoDino
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2

class HistoriaClinicaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historia_clinica)

        val textNombreMascota = this.findViewById<TextView>(R.id.text_nombreMascotaHistorial)
        val textEdadMascota = this.findViewById<TextView>(R.id.text_edadMascotaHistorial)
        val textGenero = this.findViewById<TextView>(R.id.txt_generoMascotaHistorial)
        val textPersonalidad = this.findViewById<TextView>(R.id.txt_personalidadMascotaHistorial)
        val btnUltimaVisita = this.findViewById<Button>(R.id.button_ultimaVisitaHistorial)
        val btnVacunas = this.findViewById<Button>(R.id.button_vaunasHistorial)
        val btnHistorialCompleto = this.findViewById<Button>(R.id.btn_historialCompletoHistoria)
        val imgAgregarVacuna= this.findViewById<ImageView>(R.id.img_AgregarVacunaHistorial)
        val imgAgregarVisita= this.findViewById<ImageView>(R.id.img_AgregarultimaVisitaHistorial)
        val imageDino = this.findViewById<ImageView>(R.id.imageView_mascotaHistorial)
        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomeHistoriaClinica)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionHistoriaClinica)


        val emailDueño = intent.getStringExtra("emailDueño") as String

        imgHome.setOnClickListener {
            HomeFuncioon.homeFunction(this, emailDueño)
        }

        imgCaracola.setOnClickListener {
            CaracolaFun.caracolaFunction(this)
        }



        val dino= intent.getSerializableExtra("mascota") as Mascota


        val imagenDino= TipoDino.getDrawableResource(dino.tipoMascota)

        imageDino.setImageResource(imagenDino)

        textNombreMascota.text= dino.nombreDino
        textGenero.text= dino.genero
        textEdadMascota.text = dino.edadDino.toString()
        textPersonalidad.text= dino.personalidadDino

        btnVacunas.setOnClickListener {
            val intent = Intent(this, ListaVacunaActivity::class.java)
          intent.putExtra("mascota", dino)
          startActivity(intent)

        }

        btnHistorialCompleto.setOnClickListener {

                val intent = Intent(this, HistorialCompletoActivity::class.java)
                intent.putExtra("mascota", dino)
                intent.putExtra("emailDueño", emailDueño)
                startActivity(intent)

        }

        btnUltimaVisita.setOnClickListener {

                val intent = Intent(this, UltimaVisitaActivity::class.java)
                intent.putExtra("mascota", dino)
                intent.putExtra("emailDueño", emailDueño)
                startActivity(intent)

        }

        imgAgregarVacuna.setOnClickListener {

            val intent = Intent(this,VacunaActivity::class.java)
            intent.putExtra("mascota", dino)
            intent.putExtra("emailDueño", emailDueño)
            startActivity(intent)

        }

        imgAgregarVisita.setOnClickListener {

            val intent = Intent(this, AgregarVisitaActivity::class.java)
            intent.putExtra("mascota", dino)
            intent.putExtra("emailDueño", emailDueño)
            startActivity(intent)

        }





    }
}