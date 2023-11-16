package co.edu.funlam.medisauriorex.Activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Funciones.CaracolaFun
import co.edu.funlam.medisauriorex.Funciones.HomeFuncioon
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2
import java.text.SimpleDateFormat

class VacunaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacuna_layout)

        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()

        val DaoVacuna = db.vacunaDao()

        val editTextfecha = findViewById<EditText>(R.id.editTextFecha)
        val editTextnombre = findViewById<EditText>(R.id.editTextNvacuna)
        val editTextcanti = findViewById<EditText>(R.id.editTextCantidad)
        val buttonconfir = findViewById<Button>(R.id.buttonConfVacuna)



        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomeAgregarVacuna)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionAgregarVacuna)
        val emailDueño = intent.getStringExtra("emailDueño") as String

        imgHome.setOnClickListener {
            HomeFuncioon.homeFunction(this, emailDueño)
        }


        imgCaracola.setOnClickListener {
            CaracolaFun.caracolaFunction(this)
        }




        buttonconfir.setOnClickListener {
            // Obtener el texto ingresado en los campos de edición
            val fechaVac= editTextfecha.text.toString()
            val nombreVac = editTextnombre.text.toString()
            val cantidadvac = editTextcanti.text.toString().toInt()

            // Validar datos antes de guardar
            if (editTextfecha.text.isNotEmpty() && editTextnombre.text.isNotEmpty() && editTextcanti.text.isNotEmpty()) {

                val dino= intent.getSerializableExtra("mascota") as Mascota

                val formato = SimpleDateFormat("yyyy/MM/dd")
                val date = formato.parse(fechaVac)
                val fechaVisitaLong = date?.time ?: 0

                val vacuna = Vacuna (dino.idMascota, fechaVisitaLong, nombreVac, cantidadvac)

                DaoVacuna.InsertAll(listOf(vacuna))

                Toast.makeText(this, "Vacuna añadida", Toast.LENGTH_LONG).show()

            } else {
                // Mostrar un mensaje de error si los campos están vacíos
                Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

