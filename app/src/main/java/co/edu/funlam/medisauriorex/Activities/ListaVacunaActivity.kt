package co.edu.funlam.medisauriorex.Activities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.room.Room
import co.edu.funlam.medisauriorex.Adapter.AdaptadorListaVacuna
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListaVacunaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_vacunas_layout)

        val listaLayout = this.findViewById<ListView>(R.id.listaVacunas)

        val listaVacuna = mutableListOf<Vacuna>()


        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()

        val DaoVacuna = db.vacunaDao()

        val dino= intent.getSerializableExtra("mascota") as Mascota

        val lista = DaoVacuna.getVacunasPorMascota(dino.idMascota)

        for (i in lista){
            listaVacuna.add(i)

        }


        val adaptador = AdaptadorListaVacuna(this, listaVacuna)
        listaLayout.adapter = adaptador



    }
}

