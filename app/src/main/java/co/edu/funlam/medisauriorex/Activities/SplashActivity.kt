package co.edu.funlam.medisauriorex.Activities
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import co.edu.funlam.medisauriorex.R

class SplashActivity: AppCompatActivity() {
        private val SPLASH_DELAY: Long = 3000 // Duración del SplashScreen en milisegundos (3 segundos).

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.splash_screen_layout)

            // Usar un Handler para postDelayed para abrir la actividad principal después del tiempo definido.
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_DELAY)
        }
    }

