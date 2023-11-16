package co.edu.funlam.medisauriorex.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.funlam.medisauriorex.Activities.PerfilMascotaActivity
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import co.edu.funlam.medisauriorex.Funciones.TipoDino
import co.edu.funlam.medisauriorex.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
class AdaptadorListaVacuna(private val context: Context, private var listaVacuna: List<Vacuna>) :
    RecyclerView.Adapter<AdaptadorListaVacuna.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_vacunas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacuna = listaVacuna[position]
        holder.bind(vacuna)
    }

    override fun getItemCount(): Int {
        return listaVacuna.size
    }

    fun actualizarLista(nuevaLista: List<Vacuna>) {
        listaVacuna = nuevaLista
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextViewVacunaLista: TextView = itemView.findViewById(R.id.textViewNombreVacuna)
        private val vacunaTextViewFechalista: TextView = itemView.findViewById(R.id.textViewFechaVacuna)
        private val vacunaTextViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidad)

        fun bind(vacuna: Vacuna) {
            nombreTextViewVacunaLista.text = vacuna.nombreVac

            val date = Date(vacuna.fechaVac)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaFormateada = formato.format(date)
            vacunaTextViewFechalista.text = fechaFormateada.toString()

            vacunaTextViewCantidad.text = vacuna.cantidadVac.toString()
        }
    }
}


