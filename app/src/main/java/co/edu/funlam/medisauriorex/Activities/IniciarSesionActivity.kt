package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Usuario
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.Funciones.ValidarCamposVacios
import co.edu.funlam.medisauriorex.database.DataBaseV2

class IniciarSesionActivity: AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciar_sesion)


        val editTextcorreoRegistrado =
            this.findViewById<EditText>(R.id.edit_text_correoIniciarSesio)
        val editTextcontraseñaRegistrado =
            this.findViewById<EditText>(R.id.edit_text_contraseñaIniciarSesion)
        val btnSiguienteIniciarSesion = this.findViewById<Button>(R.id.btn_InicioSesionSiguiente)



        val listEditText = listOf(editTextcorreoRegistrado, editTextcontraseñaRegistrado)

        btnSiguienteIniciarSesion.setOnClickListener {

            val db =
                Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db")
                    .allowMainThreadQueries().build()

            if (!ValidarCamposVacios.todosLlenos(listEditText)) {
                Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show()
            } else {
                val UsuarioDao = db.usuarioDao()
                val mascotaDao = db.mascotaDao()

                val coreoIngresado = editTextcorreoRegistrado.text.toString()
                val contraseñaIngresada= editTextcontraseñaRegistrado.text.toString()

                val usuarioExistente = UsuarioDao.getUsuarioByEmailAndPassword(coreoIngresado, contraseñaIngresada)

                //valido que el correo no exista en la base de datos
                if (usuarioExistente == null) {
                    Toast.makeText(this, "contraseña o correo incorrectas", Toast.LENGTH_LONG).show()
                } else {
                    //mensaje del log mostrando los usuarios de la base de datos
                    val usuariosLog: List<Usuario> = UsuarioDao.getAllUsuario()
                    Log.e("Usuarios registrados", usuariosLog.toString())


                    //intent a la siguiente actividad
                    val intent = Intent(this, ListaMascotasActivity::class.java)
                    intent.putExtra("emailDueño", coreoIngresado)
                    startActivity(intent)


                    val mascotasLog: List<Mascota> = mascotaDao.getAllMascotas()
                    Log.e("Mascotas registrados", mascotasLog.toString())

                }


            }
        }

    }
}