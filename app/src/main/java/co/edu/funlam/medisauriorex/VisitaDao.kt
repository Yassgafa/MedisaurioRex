package co.edu.funlam.medisauriorex

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VisitaDao {

    @Query("SELECT * FROM visitas")
    fun getAll():List<Visita>

    @Insert
    //vararg funciona para que la función reciba cantidad indefinida de parametros
    fun insertAll(vararg visita: List<Visita>)

    @Delete
    fun delete(visita: Visita)

}