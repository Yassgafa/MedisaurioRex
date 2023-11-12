package co.edu.funlam.medisauriorex

import androidx.room.Entity
import java.util.Date

@Entity(tableName = "visitas")
data class Visita (val tipoVisita:String, val fechaVisita:Date, val peso:Double, val condicionRespiratorio:String, val ultimoMedicamento:String, val nota:String){
}