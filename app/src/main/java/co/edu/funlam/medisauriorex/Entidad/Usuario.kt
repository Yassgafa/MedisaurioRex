package co.edu.funlam.medisauriorex.Entidad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuarios")
data class Usuario(@PrimaryKey val email: String, @ColumnInfo(name = "NombreUsuario") val nombreUsuario: String, @ColumnInfo(name = "Contraseña") val contraseña : String) : java.io.Serializable{
}