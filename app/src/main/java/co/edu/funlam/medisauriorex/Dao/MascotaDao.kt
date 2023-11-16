package co.edu.funlam.medisauriorex.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Usuario

@Dao
interface MascotaDao {

    @Query("SELECT * FROM Mascotas")
    fun getAllMascotas(): List<Mascota>


    @Query("SELECT * FROM Mascotas WHERE EmailDueño = :emailUsuario")
    fun getMascotasPorUsuario(emailUsuario: String): List<Mascota>


    @Insert
    fun insertAllMascotas( mascota: List<Mascota>)

    @Delete
    fun deleteMascotas(mascota: Mascota)
}