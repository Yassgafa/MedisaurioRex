package co.edu.funlam.medisauriorex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.funlam.medisauriorex.Dao.MascotaDao
import co.edu.funlam.medisauriorex.Dao.UsuarioDao
import co.edu.funlam.medisauriorex.Dao.VacunaDao
import co.edu.funlam.medisauriorex.Dao.VisitaDao
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Usuario
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import co.edu.funlam.medisauriorex.Entidad.Visita

@Database(entities = arrayOf(Usuario::class, Mascota::class, Visita::class, Vacuna::class), version = 1)
abstract class DataBaseV2: RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao


    abstract fun mascotaDao(): MascotaDao


    abstract fun visitaDao(): VisitaDao

    abstract fun vacunaDao(): VacunaDao

}