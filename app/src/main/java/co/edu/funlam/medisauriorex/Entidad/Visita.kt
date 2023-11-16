package co.edu.funlam.medisauriorex.Entidad
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "visitas")
data class Visita (@PrimaryKey(autoGenerate = true) val idVisita: Int, @ColumnInfo(name = "idMascota") val idMascota: Int,@ColumnInfo(name = "tipoVisita")val tipoVisita:String, @ColumnInfo(name = "fechaVisita")val fechaVisita: Long,
                   @ColumnInfo(name = "peso")val peso:Double,@ColumnInfo(name = "condicion") val condicionRespiratorio:String,
                   @ColumnInfo(name = "ultimoMedicamento")val ultimoMedicamento:String,@ColumnInfo(name = "nota") val nota:String){

    constructor(idMascota: Int, tipoVisita: String, fechaVisita: Long, peso: Double, condicionRespiratorio: String, ultimoMedicamento: String, nota :String)
            : this(0, idMascota, tipoVisita, fechaVisita, peso, condicionRespiratorio, ultimoMedicamento,nota )
}