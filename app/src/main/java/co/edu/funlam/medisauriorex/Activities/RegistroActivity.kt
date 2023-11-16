package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import co.edu.funlam.medisauriorex.Entidad.Usuario
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.Funciones.ValidarCamposVacios
import co.edu.funlam.medisauriorex.database.DataBaseV2

class RegistroActivity: AppCompatActivity (){

    private lateinit var editTextCorreo : EditText
    private lateinit var editTextContraseña : EditText
    private lateinit var editTextNombre : EditText
    private lateinit var btnCorreoSiguiente : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_layout)

        editTextCorreo= this.findViewById(R.id.edit_text_correo)
        editTextContraseña= this.findViewById(R.id.edit_text_contraseña)
        editTextNombre= this.findViewById(R.id.edit_text_Nombre)

        val btnCuentaExiste= this.findViewById<Button>(R.id.btn_cuentaRegistrada)

        //lista de edit text para validarlos
        val listEditText = listOf(editTextCorreo, editTextContraseña, editTextNombre)
        btnCorreoSiguiente= this.findViewById(R.id.btn_correoSiguiente)


        btnCorreoSiguiente.setOnClickListener {

            val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()

            if (!ValidarCamposVacios.todosLlenos(listEditText)  ){
                Toast.makeText(this, "Rellene los campos", Toast.LENGTH_LONG).show()
            }
            else
            {
                val UsuarioDao= db.usuarioDao()

                val coreoIngresado= editTextCorreo.text.toString()

                val usuarioExistente = UsuarioDao.getCorreoUsuario(coreoIngresado)

                //valido que el correo no exista en la base de datos
                if (usuarioExistente != null){
                    Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_LONG).show()
                }
                else{
                    //creo el nuevo usuario
                    val NuevoUsuario : Usuario = Usuario(coreoIngresado, editTextNombre.text.toString(), editTextContraseña.text.toString())

                    //inserta el usuario a la base de datos
                    UsuarioDao.insertAllUsuario(listOf(NuevoUsuario))

                    //mensaje del log mostrando los usuarios de la base de datos
                    val usuariosLog: List<Usuario> = UsuarioDao.getAllUsuario()
                    Log.e("Usuarios registrados", usuariosLog.toString())

                    //intent a la siguiente actividad
                    val intent = Intent (this, MascotaRegistroActivity::class.java )
                    intent.putExtra("emailDueño", NuevoUsuario.email)
                    startActivity(intent)


                }

            }



        }

        btnCuentaExiste.setOnClickListener {
            val intent = Intent (this, IniciarSesionActivity::class.java )
            startActivity(intent)
        }

    }


}