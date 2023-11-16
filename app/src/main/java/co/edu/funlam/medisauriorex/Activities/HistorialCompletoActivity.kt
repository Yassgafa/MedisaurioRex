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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HistorialCompletoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_completo)

        val textNombreMascotaCompleto = this.findViewById<TextView>(R.id.text_nombreMascotaCompletoHistorial)
        val textEdadMascotaCompleto = this.findViewById<TextView>(R.id.text_EdadMascotaCompleto)
        val textGeneroCompleto = this.findViewById<TextView>(R.id.text_generoCompleto)
        val textTipoDinoCompleto = this.findViewById<TextView>(R.id.text_tipoDinoCompleto)
        val textUltimaVisitaCompleto = this.findViewById<TextView>(R.id.text_ultimaVisitaCompleto)
        val textTipoVisitaCompleto = this.findViewById<TextView>(R.id.text_tipoVistiaCompleto)
        val textUltimoMedicamentoCompleto = this.findViewById<TextView>(R.id.text_ultimoMedCompleto)
        val imageDino = this.findViewById<ImageView>(R.id.imageView_mascotaCompletoHistorial)

        val emailDueño = intent.getStringExtra("emailDueño") as String
        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomeHistorialCompleto)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionHistorialCompleto)

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
            textNombreMascotaCompleto.text= dino.nombreDino
            textEdadMascotaCompleto.text= dino.edadDino.toString()
            textGeneroCompleto.text= dino.genero
            textTipoDinoCompleto.text= dino.tipoMascota

            val date= Date(i.fechaVisita)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaFormateada = formato.format(date)

            textUltimaVisitaCompleto.text= fechaFormateada.toString()

            textTipoVisitaCompleto.text= i.tipoVisita
            textUltimoMedicamentoCompleto.text= i.ultimoMedicamento

        }

        val imagenDino= TipoDino.getDrawableResource(dino.tipoMascota)

        imageDino.setImageResource(imagenDino)




    }
}