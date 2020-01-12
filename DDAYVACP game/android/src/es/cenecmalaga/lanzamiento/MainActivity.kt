package es.cenecmalaga.lanzamiento

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import es.cenecmalaga.ddayvacp.R
import es.cenecmalaga.preferencias.Preferencias
import android.widget.TextView
import es.cenecmalaga.constantes.Constantes


/**
 * Actividad Inicial de Android, que se encargará de elegir género y modo de movimiento, o de ir a opciones.
 * @author Miguel Páramos
 */
class MainActivity : AppCompatActivity() {

    private val genero by lazy{ findViewById<Switch>(R.id.switchGenero) } //Contiene un String definido en @String, que puede ser "masculino" o "femenino".
    private val modoMovimiento by lazy{ findViewById<Switch>(R.id.switchModoMovimiento) } //Contiene un String definido en @String, que puede ser "discreto" o "continuo".
    private val preferencias by lazy{  getSharedPreferences("es.cenecmalaga.ddayvacp_preferences",Context.MODE_PRIVATE) } //Archivo de preferencias, para no tener que estar cambiando continuamente la opción por defecto.
    private val textViewVersion by lazy{ findViewById<TextView>(R.id.gameVersion)} //Textview en el que pondremos la versión del juego
    /**
     * Establece el layout.
     * @param savedInstanceState no se usa explicitamente, bundle inicial de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.setContentView(es.cenecmalaga.ddayvacp.R.layout.activity_main)

        //Obtengo y establezco la versión del juego
        textViewVersion.text= "v."+Constantes.gameVersion


    }

    /**
     * Se sobreescribe onStart para que la pantalla principal coja la opción seleccionada en la pantalla de preferencias al volver atrás.
     */
    override fun onStart() {
        super.onStart()
        //Si las preferencias establecen que el genero es femenino, lo rescatamos para esta app. Y al contrario.
        if(preferencias.getBoolean("genero",false)){
            genero.isChecked=true;
            genero.setText(R.string.femenino)
        }else{
            genero.isChecked=false;
            genero.setText(R.string.masculino)
        }

        //Si las preferencias establecen que el modo de movimiento es continuo, lo rescatamos para esta app. Y al contrario.
        if(preferencias.getBoolean("modoMovimiento",false)){
            modoMovimiento.isChecked=true;
            modoMovimiento.setText(R.string.continuo)

        }else{
            modoMovimiento.isChecked=false;
            modoMovimiento.setText(R.string.discreto)

        }
    }

    /**
     * Evento onClick del botón jugar, que lanza la actividad AndroidLauncher que lanza el Juego
     * @param view vista que se cliqueó. El botón en este caso.
     */
    fun lanzarJuego(view: View) {
        val i = Intent(
                this,
                AndroidLauncher::class.java)
        this.startActivity(i)
    }

    /**
     * Evento onClick del switch de género, que cambia el género entre masculino o femenino, para comenzar así el juego.
     * @param view vista que se cliqueó. El switch en este caso.
     */
    fun cambiarGenero(view: View) {
        if ((view as Switch).isChecked) {
            genero.setText(R.string.femenino)
        } else {
            genero.setText(R.string.masculino)
        }
    }

    /**
     * Evento onClick del switch de modo de Movimiento, que cambia el modo de movimiento entre discreto y continuo, para comenzar así el juego.
     * @param view vista que se cliqueó. El switch en este caso.
     */
    fun cambiarModoMovimiento(view: View) {
        if ((view as Switch).isChecked) {
            modoMovimiento.setText(R.string.continuo)
        } else {
            modoMovimiento.setText(R.string.discreto)
        }
    }

    /**
     * Evento onclick del imageView de la llave inglesa. Lleva al usuario a la pantalla de preferencias.
     * @param view vista que se cliqueó. El imageView en este caso.
     */
    fun irAOpciones(view: View) {
        val i = Intent(this, Preferencias::class.java)
        startActivity(i)
    }
}

