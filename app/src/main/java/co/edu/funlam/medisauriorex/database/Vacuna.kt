package co.edu.funlam.medisauriorex.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "vacunas")
data class Vacuna(@PrimaryKey val fechaVac:String, val nombreVac:String, val cantidadVac: String ) {
}



