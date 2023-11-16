package co.edu.funlam.medisauriorex.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Visita
import co.edu.funlam.medisauriorex.Funciones.CaracolaFun
import co.edu.funlam.medisauriorex.Funciones.HomeFuncioon
import co.edu.funlam.medisauriorex.Funciones.TipoDino
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2
import java.util.Date

class UltimaVisitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ultima_visita)

        val textNombreMascotaUltimaVisita = this.findViewById<TextView>(R.id.text_nombreMascotaUltimaVisita)
        val textCondicion = this.findViewById<TextView>(R.id.text_condicionUltimaVisita)
        val textMedicamento = this.findViewById<TextView>(R.id.text_UltimoMedicamenteUltimaVisita)
        val textNota = this.findViewById<TextView>(R.id.text_notaUltimaVisita)
        val textPeso = this.findViewById<TextView>(R.id.text_PesoUltimaVisita)
        val imageDino = this.findViewById<ImageView>(R.id.imageView_mascotaUltimaVisita)


        val emailDueño = intent.getStringExtra("emailDueño") as String
        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomeUltimaVisita)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionUltima)

        imgHome.setOnClickListener {
            HomeFuncioon.homeFunction(this, emailDueño)
        }

        imgCaracola.setOnClickListener {
            CaracolaFun.caracolaFunction(this)
        }



        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()
        val visitaDao = db.visitaDao()

        val dino = intent.getSerializableExtra("mascota")as Mascota

        val visita = visitaDao.getUltimaVisita(dino.idMascota) as List<Visita>


        for (i in visita)
        {
            textNombreMascotaUltimaVisita.text=dino.nombreDino
            textCondicion.text= i.condicionRespiratorio
            textMedicamento.text= i.ultimoMedicamento
            textNota.text= i.nota
            textPeso.text= i.peso.toString()

        }


        val imagenDino= TipoDino.getDrawableResource(dino.tipoMascota)

        imageDino.setImageResource(imagenDino)



    }
}