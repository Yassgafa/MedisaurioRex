package co.edu.funlam.medisauriorex

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.room.Room
import java.text.SimpleDateFormat

class AgregarVisitaActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_visita)

        val db = Room.databaseBuilder(applicationContext, AppDatabaseVisita::class.java, "room.db").allowMainThreadQueries().build()
        val visitaDao = db.visitaDao()


        val editTextFechaVisita = findViewById<EditText>(R.id.editTextFechaVisita)
        val editTextPeso = findViewById<EditText>(R.id.editTextPeso)
        val editTextUltimoMedicamento = findViewById<EditText>(R.id.editTextUltimoMedicamento)
        val editTextNota = findViewById<EditText>(R.id.editTextNota)

        //Spinner tipo visita
        val listaTipoVisita = mutableListOf<String>()
        val tp1 = "Rutinaria"
        val tp2 = "Urgencia"
        val tp3 = "Laboratorio"

        listaTipoVisita.add(tp1)
        listaTipoVisita.add(tp2)
        listaTipoVisita.add(tp3)

        //Creación del adaptador XML
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaTipoVisita)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        //Configurar spinner
        val spinner = this.findViewById<Spinner>(R.id.spinnerTipoVisita)
        spinner.adapter = adaptador



        //Spinner condicion respiratoria
        val listaCondicionRespiratoria = mutableListOf<String>()
        val cr1 = "Estable"
        val cr2 = "No estable"

        listaCondicionRespiratoria.add(cr1)
        listaCondicionRespiratoria.add(cr2)


        //Creación del adaptador XML
        val adaptador2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCondicionRespiratoria)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        //Configurar spinner
        val spinner2 = this.findViewById<Spinner>(R.id.spinnerCondicionRespiratoria)
        spinner2.adapter = adaptador2

        val btnConfirmar = findViewById<Button>(R.id.btnConfirmarVisita)
        btnConfirmar.setOnClickListener {

            if (editTextFechaVisita.text.isEmpty() && editTextPeso.text.isEmpty() && editTextUltimoMedicamento.text.isEmpty() && editTextNota.text.isEmpty()){

                Toast.makeText(this, "No dejes campos vacios", Toast.LENGTH_SHORT).show()
            }
            else {

                val formato = SimpleDateFormat("yyyy/MM/dd")
                val tipoVisita : String = spinner.selectedItem as String
                val fechaVisita = editTextFechaVisita.text.toString()
                val date = formato.parse(fechaVisita)
                val pesoMascota = editTextPeso.text.toString().toDouble()
                val condicionR : String = spinner2.selectedItem as String
                val ultimoMedi = editTextUltimoMedicamento.text.toString()
                val notaVisita = editTextNota.text.toString()


                val guardarVisita = Visita(tipoVisita, date, pesoMascota, condicionR, ultimoMedi, notaVisita)
                visitaDao.insertAll(listOf(guardarVisita))

                /*val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)*/

            }

        }

    }
}

