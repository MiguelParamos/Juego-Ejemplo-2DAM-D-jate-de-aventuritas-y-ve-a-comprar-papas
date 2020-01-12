package es.cenecmalaga.preferencias

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceActivity
import es.cenecmalaga.ddayvacp.R

/**
 * Actividad para guardar el género y el modo de movimiento en preferencias de forma permanente.
 * LibGdx no parece llevarse muy bien con PreferenceFragment ni el PreferenceActivity de AndroidX.
 * Es por eso que usamos esta versión antigua de la clase.
 */
class Preferencias : PreferenceActivity() {

    /**
     * Método que enlaza la actividad con el fichero preferencias de res/xml
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.addPreferencesFromResource(R.xml.preferencias)
    }
}
