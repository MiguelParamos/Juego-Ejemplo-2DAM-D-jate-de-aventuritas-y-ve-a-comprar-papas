package es.cenecmalaga.entrada;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;

import es.cenecmalaga.personajes.Jugador;

/**
 * Clase que implementa el escuchador de teclado de un personaje
 * @author Miguel Páramos
 */
public class EscuchadorTecladoJugador extends EscuchadorTeclado {
    private Jugador jugador; //El jugador que se va a mover con este escuchador

    public EscuchadorTecladoJugador(Jugador j){
        super(j.getCamara(),j.getMapa());
        jugador=j;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.W:
                jugador.moverTile('u');
                jugador.moverCamaraTile('u');
                break;
            case Input.Keys.S:
                jugador.moverTile('d');
                jugador.moverCamaraTile('d');
                 break;
            case Input.Keys.A:
                jugador.moverTile('l');
                jugador.moverCamaraTile('l');
                break;
            case Input.Keys.D:
                jugador.moverTile('r');
                jugador.moverCamaraTile('r');
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
