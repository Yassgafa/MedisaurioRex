package co.edu.funlam.medisauriorex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import co.edu.funlam.medisauriorex.database.AppDatabase
import co.edu.funlam.medisauriorex.database.Vacuna
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class VacunaActivity2 : AppCompatActivity(){
    private  lateinit var ListViewdb: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vacuna2_layout)
        ListViewdb = findViewById(R.id.listaVacunas)
        cargarListViewdb()
    }
    private fun cargarListViewdb() {
        GlobalScope.launch(Dispatchers.Main) {
            val vacuna = obtenerVacunaDesdeBD()
            val adapter = ArrayAdapter(this@VacunaActivity2, android.R.layout.simple_list_item_1, vacuna)
            ListViewdb.adapter = adapter
        }
    }

    private suspend fun obtenerVacunaDesdeBD(): List<Vacuna> {
        return withContext(Dispatchers.IO) {
            val db = AppDatabase.getInstance(this@VacunaActivity2)
            val t1Dao = db.v1Dao()
            return@withContext t1Dao.getAll().map { Vacuna(it.fechaVac, it.nombreVac, it.cantidadVac) }
        }
    }


}
