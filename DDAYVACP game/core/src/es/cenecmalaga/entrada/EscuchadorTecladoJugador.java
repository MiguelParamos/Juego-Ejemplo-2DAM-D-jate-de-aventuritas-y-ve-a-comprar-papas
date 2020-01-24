package es.cenecmalaga.entrada;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;

import es.cenecmalaga.personajes.Jugador;

/**
 * Clase que implementa el escuchador de teclado de un personaje
 * @author Miguel Páramos
 */
public class EscuchadorTecladoJugador extends EscuchadorTeclado {
    private Jugador jugador; //El jugador que se va a mover con este escuchador
    private boolean tipoMovimiento; //Tipo de movimiento del jugador en la pantalla de tiles. True-> Continuo, False -> Discreto.

    public EscuchadorTecladoJugador(Jugador j,boolean tipoMovimiento){
        super(j.getCamara(),j.getMapa());
        jugador=j;
        this.tipoMovimiento=tipoMovimiento;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(!tipoMovimiento) {
            switch (keycode) {
                case Input.Keys.W:
                    jugador.moverCamaraTile('u');
                    jugador.moverJugadorTile('u', keepCameraInBounds());

                    break;
                case Input.Keys.S:
                    jugador.moverCamaraTile('d');
                    jugador.moverJugadorTile('d', keepCameraInBounds());

                    break;
                case Input.Keys.A:
                    jugador.moverCamaraTile('l');
                    jugador.moverJugadorTile('l', keepCameraInBounds());

                    break;
                case Input.Keys.D:
                    jugador.moverCamaraTile('r');
                    jugador.moverJugadorTile('r', keepCameraInBounds());

                    break;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        Gdx.app.log("typed",character+"");
        if(tipoMovimiento) {
            switch (character) {
                case 'w':
                    jugador.moverJugadorPixels('u');
                    //jugador.moverCamaraPixels('u');
                    break;
                case 's':
                    jugador.moverJugadorPixels('d');
                    break;
                case 'a':
                    jugador.moverJugadorPixels('l');
                    break;
                case 'd':
                    jugador.moverJugadorPixels('r');
                    break;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
