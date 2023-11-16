package co.edu.funlam.medisauriorex.Funciones

import android.widget.ImageView
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.R

class TipoDino {
    companion object{

        @JvmStatic
        fun getDrawableResource(tipoMascota: String): Int {
            return when (tipoMascota) {
                "T-rex" -> R.drawable.tiranosaurio
                "Velociraptor" -> R.drawable.velociraptor
                "Triceratops" -> R.drawable.triceratops
                "Pterodáctilo" -> R.drawable.pterodactilo
                "Estegosaurio" -> R.drawable.estegosaurio
                "Spinosurus" -> R.drawable.spinosaurus
                "Brontosaurio" -> R.drawable.brontosaurio
                "Mamut" -> R.drawable.mamut_lanudo
                else -> R.drawable.triceratops // Puedes establecer una imagen predeterminada para casos no definidos
            }
        }

    }

}