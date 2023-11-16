package co.edu.funlam.medisauriorex.Activities

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class RecordatorioDino : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recordatorio_dino)

        val textRecordatorio = this.findViewById<TextView>(R.id.textViewRecordatorio)


        val mascota = intent.getSerializableExtra("mascota") as Mascota

        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()
        val vacunaDao= db.vacunaDao()
        val vacuna = vacunaDao.getUltimaVacuna(mascota.idMascota)


        for(i in vacuna) {

            val date = Date(i.fechaVac)
            val calendar = Calendar.getInstance()
            calendar.time = date

            // Agregar un año a la fecha
            calendar.add(Calendar.YEAR, 1)

            // Convertir la fecha con un año más a String en el formato deseado
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaFormateada = formato.format(calendar.time)

            // Asignar la fecha con un año más al textRecordatorio
            textRecordatorio.text ="Su proxima vacuna será en:  $fechaFormateada"

        }




    }

}
