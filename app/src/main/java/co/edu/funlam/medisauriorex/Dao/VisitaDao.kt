package co.edu.funlam.medisauriorex.Dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Visita

@Dao
interface VisitaDao {

    @Query("SELECT * FROM visitas")
    fun getAllVisita():List<Visita>

    @Insert
    //vararg funciona para que la función reciba cantidad indefinida de parametros
    fun insertAllVisita(visita: List<Visita>)

    @Delete
    fun deleteVisita(visita: Visita)

    @Query("SELECT * FROM visitas WHERE idMascota = :idMascota ORDER BY fechaVisita DESC LIMIT 1")
    fun getUltimaVisita(idMascota: Int): List<Visita>

}