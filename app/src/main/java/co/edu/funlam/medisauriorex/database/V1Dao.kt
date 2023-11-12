package co.edu.funlam.medisauriorex.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface V1Dao {
    @Query("SELECT * FROM vacunas")
    fun getAll(): List<Vacuna>
    @Insert
    fun InsertAll(vacuna: Vacuna)
    @Delete
    fun delete(vacuna: Vacuna)
}