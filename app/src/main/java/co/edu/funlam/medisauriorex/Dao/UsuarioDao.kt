package co.edu.funlam.medisauriorex.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.funlam.medisauriorex.Entidad.Usuario

@Dao
interface UsuarioDao {

    @Query("SELECT  * FROM Usuarios")
    fun getAllUsuario(): List<Usuario>


    @Insert
    fun insertAllUsuario(  usuario: List<Usuario>)


    @Delete
    fun deleteUsuario(usuario: Usuario)



    @Query("SELECT * FROM Usuarios WHERE email = :correo")
    fun getCorreoUsuario(correo: String): Usuario?


    @Query("SELECT * FROM Usuarios WHERE email = :correo AND contraseña = :contrasena")
    fun getUsuarioByEmailAndPassword(correo: String, contrasena: String): Usuario?



}