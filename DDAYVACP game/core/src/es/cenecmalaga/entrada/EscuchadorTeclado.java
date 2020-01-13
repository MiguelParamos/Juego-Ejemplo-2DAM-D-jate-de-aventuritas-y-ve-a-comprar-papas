package es.cenecmalaga.entrada;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class EscuchadorTeclado implements InputProcessor {
    private OrthographicCamera camera; //Aquí capturaremos la cámara de la clase del juego
    private TiledMap map;
    private char movimientoAutomatico;

    public EscuchadorTeclado(OrthographicCamera oc,TiledMap tm){
        super();
        this.camera=oc;
        this.map=tm;
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode){
            case Keys.LEFT:
                camera.position.x = Math.max(camera.position.x - 1, 0); //Se mueve la cámara hasta el mínimo del mapa
            break;
            case Keys.RIGHT:
                camera.position.x = Math.min(camera.position.x + 1,
                        (int)map.getProperties().get("width"));
                //Se mueve la cámara hasta el máximo del mapa
                break;
            case Keys.UP:
                camera.position.y = Math.min(camera.position.y + 1,
                        (int)map.getProperties().get("height"));
                //Se mueve la cámara hasta el máximo del mapa
                break;
            case Keys.DOWN:
                camera.position.y = Math.max(camera.position.y - 1,0);
                //Se mueve la cámara hasta el máximo del mapa
                break;
            case Keys.CONTROL_RIGHT:
                //Establecemos patrón de movimiento: Primero siempre
                //me voy a y=0, y desde ahí voy hacia x=(anchura del mapa) y empiezo a moverme
                //en cuadrados en todo el borde del mapa.

                break;
        }
        camera.update();
        return true;
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
