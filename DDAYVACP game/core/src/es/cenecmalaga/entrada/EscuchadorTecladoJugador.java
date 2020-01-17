package es.cenecmalaga.entrada;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import es.cenecmalaga.personajes.Jugador;

/**
 * Clase que implementa el escuchador de teclado de un personaje
 * @author Miguel Páramos
 */
public class EscuchadorTecladoJugador implements InputProcessor {
    private Jugador jugador; //El jugador que se va a mover con este escuchador

    public EscuchadorTecladoJugador(Jugador j){
        jugador=j;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                jugador.moverTile('u');
                break;
            case Input.Keys.S:
                jugador.moverTile('d');
                 break;
            case Input.Keys.A:
                jugador.moverTile('l');
                break;
            case Input.Keys.D:
                jugador.moverTile('r');
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
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
