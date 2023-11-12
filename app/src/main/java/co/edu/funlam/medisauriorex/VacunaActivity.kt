package co.edu.funlam.medisauriorex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.room.Room
import co.edu.funlam.medisauriorex.database.AppDatabase
import co.edu.funlam.medisauriorex.database.Vacuna
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope


class VacunaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacuna_layout)
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "room.db").allowMainThreadQueries().build()

        val v1Dao = db.v1Dao()

        val editTextfecha = findViewById<EditText>(R.id.editTextFecha)
        val editTextnombre = findViewById<EditText>(R.id.editTextNvacuna)
        val editTextcanti = findViewById<EditText>(R.id.editTextCantidad)
        val buttonconfir = findViewById<Button>(R.id.buttonConfVacuna)

        buttonconfir.setOnClickListener {
            // Obtener el texto ingresado en los campos de edición
            val fechaVac= editTextfecha.text.toString()
            val nombreVac = editTextnombre.text.toString()
            val cantidadvac = editTextcanti.text.toString()

            // Validar datos antes de guardar
            if (fechaVac.isNotEmpty() && nombreVac.isNotEmpty() && cantidadvac.isNotEmpty()) {

                val vacuna =Vacuna (fechaVac, nombreVac, cantidadvac)


                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            v1Dao.InsertAll(vacuna)
                        } catch (e: Exception) {
                            Log.e("Vacuna", "Error en el registro de vacunas del Dino: ${e.message}")
                        }
                    }


                    val intent = Intent(this@VacunaActivity, VacunaActivity2::class.java)
                    startActivity(intent)
                }
            } else {
                // Mostrar un mensaje de error si los campos están vacíos
                Toast.makeText(this, "Por favor, ingrese todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}