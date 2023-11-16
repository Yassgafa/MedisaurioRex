package co.edu.funlam.medisauriorex.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import co.edu.funlam.medisauriorex.Adapter.AdaptadorListaMascotasDueño
import co.edu.funlam.medisauriorex.Dao.MascotaDao
import co.edu.funlam.medisauriorex.Entidad.Mascota
import co.edu.funlam.medisauriorex.Entidad.Usuario
import co.edu.funlam.medisauriorex.R
import co.edu.funlam.medisauriorex.database.DataBaseV2

class ListaMascotasActivity : AppCompatActivity() {

    private lateinit var emailDueño: String
    private lateinit var listaMascotasCorreoDueño: List<Mascota>
    private lateinit var adaptador: AdaptadorListaMascotasDueño
    private lateinit var mascotaDao: MascotaDao
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_mascotas)

        val btnAgregarMascotaListaDueño = findViewById<Button>(R.id.btn_agregarMascotaListaDueño)
        recyclerView = findViewById(R.id.recyclerView_mascotas_dueño)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = Room.databaseBuilder(applicationContext, DataBaseV2::class.java, "room.db").allowMainThreadQueries().build()
        mascotaDao = db.mascotaDao()

        emailDueño = intent.getStringExtra("emailDueño") as String

        listaMascotasCorreoDueño = mascotaDao.getMascotasPorUsuario(emailDueño)

        val listaMostrada = mutableListOf<Mascota>()
        for (i in listaMascotasCorreoDueño) {
            listaMostrada.add(i)
        }

        adaptador = AdaptadorListaMascotasDueño(this, listaMostrada, emailDueño)
        recyclerView.adapter = adaptador





        btnAgregarMascotaListaDueño.setOnClickListener {
            val intent = Intent(this, MascotaRegistroActivity::class.java)
            intent.putExtra("emailDueño", emailDueño)
            startActivity(intent)


        }
    }

    override fun onResume() {
        super.onResume()
        if (::mascotaDao.isInitialized) {
            actualizarListaMascotas()
        }
    }

    private fun actualizarListaMascotas() {
        listaMascotasCorreoDueño = mascotaDao.getMascotasPorUsuario(emailDueño)
        adaptador.actualizarLista(listaMascotasCorreoDueño)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }
}

