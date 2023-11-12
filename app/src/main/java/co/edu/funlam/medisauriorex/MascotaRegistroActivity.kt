package co.edu.funlam.medisauriorex

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MascotaRegistroActivity : AppCompatActivity ()  {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
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

    }

}