package co.edu.funlam.medisauriorex.Activities
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Visita
import co.edu.funlam.medisauriorex.Funciones.CaracolaFun
import co.edu.funlam.medisauriorex.Funciones.HomeFuncioon
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2
import java.text.SimpleDateFormat
import java.util.Date

class AgregarVisitaActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_visita)

        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()
        val visitaDao = db.visitaDao()


        val editTextFechaVisita = findViewById<EditText>(R.id.editTextFechaVisita)
        val editTextPeso = findViewById<EditText>(R.id.editTextPeso)
        val editTextUltimoMedicamento = findViewById<EditText>(R.id.editTextUltimoMedicamento)
        val editTextNota = findViewById<EditText>(R.id.editTextNota)

        val imgHome = this.findViewById<ImageView>(R.id.imageView_HomeAgregarVisita)
        val imgCaracola = this.findViewById<ImageView>(R.id.imageView_ConfiguracionAgregarVisita)

        val emailDueño = intent.getStringExtra("emailDueño") as String

        imgHome.setOnClickListener {
            HomeFuncioon.homeFunction(this, emailDueño)
        }

        imgCaracola.setOnClickListener {
            CaracolaFun.caracolaFunction(this)
        }



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

                val dino = intent.getSerializableExtra("mascota")as Mascota

                val formato = SimpleDateFormat("yyyy/MM/dd")
                val tipoVisita : String = spinner.selectedItem as String
                val fechaVisita = editTextFechaVisita.text.toString()


                val date = formato.parse(fechaVisita)

                val fechaVisitaLong = date?.time ?: 0

                val pesoMascota = editTextPeso.text.toString().toDouble()
                val condicionR : String = spinner2.selectedItem as String
                val ultimoMedi = editTextUltimoMedicamento.text.toString()
                val notaVisita = editTextNota.text.toString()


                val guardarVisita = Visita(dino.idMascota,tipoVisita, fechaVisitaLong, pesoMascota, condicionR, ultimoMedi, notaVisita)
                visitaDao.insertAllVisita(listOf(guardarVisita))

                Toast.makeText(this, "Visita agregada, puede regresar", Toast.LENGTH_SHORT).show()


                //mensaje del log mostrando los usuarios de la base de datos
                val visitasLog: List<Visita> = visitaDao.getAllVisita()
                Log.e("Mascotas registrados", visitasLog.toString())

            }

        }

    }
}

