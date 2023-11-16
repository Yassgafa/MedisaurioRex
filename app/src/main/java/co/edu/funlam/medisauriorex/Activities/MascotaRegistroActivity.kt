package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.Funciones.ValidarCamposVacios
import co.edu.funlam.medisauriorex.database.DataBaseV2

class MascotaRegistroActivity : AppCompatActivity ()  {
    private lateinit var editTextNombreMascota: EditText
    private lateinit var editTextEdadMascota: EditText
    private lateinit var editTextPersonalidadMascota: EditText
    private lateinit var rdbGeneroMascota: RadioGroup
    private lateinit var dueño: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mascota_registro)
        val spinner = findViewById<Spinner>(R.id.spinner_tipoMascota)
        // Crear un array de datos de ejemplo
        val datos = arrayOf("T-rex", "Velociraptor", "Triceratops ", "Pterodáctilo", "Estegosaurio", "Spinosurus", "Brontosaurio", "Mamut")

        // Crear un ArrayAdapter para los datos y el diseño predeterminado
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item , datos)

        // Establecer el adaptador en el Spinner
        spinner.adapter = adapter
        // Configurar la descripción accesible para el Spinner
        spinner.contentDescription = "Selecciona una opción"


        editTextNombreMascota= this.findViewById(R.id.edittext_nombreMascota)
        editTextPersonalidadMascota=this.findViewById(R.id.edittext_personalidadMascota)
        editTextEdadMascota= this.findViewById(R.id.edittext_edadMascota)

        val btnMascotaSiguiente= this.findViewById<Button>(R.id.btn_Mascotaiguiente)



        val listEditText = listOf(editTextNombreMascota, editTextPersonalidadMascota, editTextEdadMascota)


        //oClick del botón
        btnMascotaSiguiente.setOnClickListener {

            val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()

            if (!ValidarCamposVacios.todosLlenos(listEditText) && rdbGeneroMascota.checkedRadioButtonId ==-1 && spinner.selectedItemPosition == AdapterView.INVALID_POSITION){
                Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show()
            }else{

                val genero: String
                val rdbMacho = this.findViewById<RadioButton>(R.id.rdb_generoMacho)
                if (rdbMacho.isChecked){
                    genero = "Macho"
                }else{
                    genero = "Hembra"
                }


                val mascotaDao = db.mascotaDao()

                dueño= intent.getStringExtra("emailDueño") as String

                    val mascotaNueva = Mascota(dueño, spinner.selectedItem.toString(), genero , editTextNombreMascota.text.toString(), editTextEdadMascota.text.toString().toInt(), editTextPersonalidadMascota.text.toString() )

                    mascotaDao.insertAllMascotas(listOf(mascotaNueva))


                //intent a la siguiente actividad
                    val intent = Intent (this, ListaMascotasActivity::class.java )
                    intent.putExtra("emailDueño", mascotaNueva.correoDueño)
                    startActivity(intent)



                //mensaje del log mostrando los usuarios de la base de datos
                    val mascotasLog: List<Mascota> = mascotaDao.getAllMascotas()
                    Log.e("Mascotas registrados", mascotasLog.toString())

            }
        }

    }

    override fun onResume() {
        super.onResume()
       // dueño= intent.getSerializableExtra("Dueño") as Usuario

    }

}