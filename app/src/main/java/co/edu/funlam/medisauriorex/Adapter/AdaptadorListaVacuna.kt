package co.edu.funlam.medisauriorex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import co.edu.funlam.medisauriorex.Entidad.Vacuna
import co.edu.funlam.medisauriorex.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdaptadorListaVacuna (context: Context, private val vacunas: List<Vacuna>): BaseAdapter(){
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return vacunas.size
    }

    override fun getItem(position: Int): Any {
        return vacunas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = inflater.inflate(R.layout.item_lista_vacunas, parent, false)
            viewHolder = ViewHolder()
            viewHolder.nombreTextViewVacunaLista = view.findViewById(R.id.textViewNombreVacuna)
            viewHolder.vacunaTextViewFechalista = view.findViewById(R.id.textViewFechaVacuna)
            viewHolder.vacunaTextViewCantidad = view.findViewById(R.id.textViewCantidad)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val itemVacuna = vacunas[position]
        viewHolder.nombreTextViewVacunaLista.text = itemVacuna.nombreVac

        val date = Date(itemVacuna.fechaVac)
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaFormateada = formato.format(date)

        viewHolder.vacunaTextViewFechalista.text = fechaFormateada.toString()
        viewHolder.vacunaTextViewCantidad.text = itemVacuna.cantidadVac.toString()

        return view
    }

    private class ViewHolder {
        lateinit var nombreTextViewVacunaLista: TextView
        lateinit var vacunaTextViewFechalista: TextView
        lateinit var vacunaTextViewCantidad: TextView

    }

}