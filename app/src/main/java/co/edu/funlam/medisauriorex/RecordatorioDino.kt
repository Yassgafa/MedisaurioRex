package co.edu.funlam.medisauriorex

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity


class RecordatorioDino : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recordatorio_dino)

        val buttonConfirmar = findViewById<Button>(R.id.buttonConfirmarCita)

        buttonConfirmar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
