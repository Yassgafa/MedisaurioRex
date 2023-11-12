package co.edu.funlam.medisauriorex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Vacuna::class), version = 1)
abstract class AppDatabase : RoomDatabase (){
    abstract fun v1Dao(): V1Dao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "room.db"
                    ).build()
                }
            }
            return instance!!
        }

    }
}



