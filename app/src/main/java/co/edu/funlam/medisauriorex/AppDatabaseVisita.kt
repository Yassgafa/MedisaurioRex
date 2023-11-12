package co.edu.funlam.medisauriorex

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Visita::class), version = 1)
abstract class AppDatabaseVisita:RoomDatabase() {
    abstract fun visitaDao():VisitaDao
}