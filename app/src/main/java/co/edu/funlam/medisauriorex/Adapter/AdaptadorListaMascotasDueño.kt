package co.edu.funlam.medisauriorex.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.R
import androidx.recyclerview.widget.RecyclerView
import co.edu.funlam.medisauriorex.Activities.PerfilMascotaActivity
import co.edu.funlam.medisauriorex.Funciones.TipoDino

class AdaptadorListaMascotasDueño(private val context: Context, private var listaDinos: List<Mascota>, private val emailDueño: String) :
    RecyclerView.Adapter<AdaptadorListaMascotasDueño.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lista_mascotas_duenho, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaDinos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val masco = listaDinos[position]
        holder.bind(masco)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PerfilMascotaActivity::class.java)
            intent.putExtra("mascota", masco)
            intent.putExtra("emailDueño", emailDueño)
            context.startActivity(intent)
        }

    }

    fun actualizarLista(nuevaLista: List<Mascota>) {
        listaDinos = nuevaLista
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtNombreMascotaAdapter: TextView = itemView.findViewById(R.id.txt_nombreMascotaPerfil_Dueño)
        private val imgListaMascotaAdapter: ImageView = itemView.findViewById(R.id.img_listaMascotaPerfilDueño)

        fun bind(mascota: Mascota) {
            txtNombreMascotaAdapter.text = mascota.nombreDino

            val imagenDino=TipoDino.getDrawableResource(mascota.tipoMascota)

            imgListaMascotaAdapter.setImageResource(imagenDino)

        }
    }
}


