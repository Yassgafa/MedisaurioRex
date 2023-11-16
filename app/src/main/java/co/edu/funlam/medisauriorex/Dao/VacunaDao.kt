package co.edu.funlam.medisauriorex.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import co.edu.funlam.medisauriorex.Entidad.Visita

@Dao
interface VacunaDao {
    @Query("SELECT * FROM vacunas")
    fun getAll(): List<Vacuna>
    @Insert
    fun InsertAll(vacuna: List<Vacuna>)
    @Delete
    fun delete(vacuna: Vacuna)

    @Query("SELECT * FROM vacunas WHERE idMascota = :idMascota")
    fun getVacunasPorMascota(idMascota: Int): List<Vacuna>

    @Query("SELECT * FROM vacunas WHERE idMascota = :idMascota ORDER BY fechaVacuna DESC LIMIT 1")
    fun getUltimaVacuna(idMascota: Int): List<Vacuna>


}