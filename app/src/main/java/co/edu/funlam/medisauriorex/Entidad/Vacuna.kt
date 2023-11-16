package co.edu.funlam.medisauriorex.Entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName= "vacunas")
data class Vacuna(@PrimaryKey (autoGenerate = true) val idVacuna: Int,
                  @ColumnInfo (name = "idMascota") val idMascota : Int,
                  @ColumnInfo(name = "fechaVacuna") val fechaVac:Long,
                  @ColumnInfo (name = "nombreVacuna")val nombreVac:String,
                  @ColumnInfo(name = "cantidad")val cantidadVac: Int )  {

    constructor(idMascota: Int, fechaVac: Long, nombreVac: String, cantidadVac: Int)
            : this(0, idMascota, fechaVac, nombreVac, cantidadVac )
}



