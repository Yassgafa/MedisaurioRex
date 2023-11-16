package co.edu.funlam.medisauriorex.Entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "Mascotas")
data class Mascota (@PrimaryKey(autoGenerate = true) val idMascota: Int, @ColumnInfo(name = "EmailDueño") val correoDueño: String, @ColumnInfo(name = "TipoMascota") val tipoMascota: String, @ColumnInfo(name = "Genero") val genero : String,
                    @ColumnInfo(name = "NombreDinosaurio") val nombreDino: String, @ColumnInfo(name = "EdadDino") val edadDino: Int,
                    @ColumnInfo(name = "Personalidad") val personalidadDino: String): java.io.Serializable {

    constructor(correoDueño: String, tipoMascota: String, genero: String, nombreDino: String, edadDino: Int, personalidadDino: String)
            : this(0, correoDueño, tipoMascota, genero, nombreDino, edadDino, personalidadDino)
}