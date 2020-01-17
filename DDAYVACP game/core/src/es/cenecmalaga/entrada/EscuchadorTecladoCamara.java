package es.cenecmalaga.entrada;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector3;

public class EscuchadorTecladoCamara implements InputProcessor {
    private OrthographicCamera camera; //Aquí capturaremos la cámara de la clase del juego
    private TiledMap map;
    private char movimientoAutomatico;

    public EscuchadorTecladoCamara(OrthographicCamera oc, TiledMap tm){
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
            case Keys.PLUS:
                camera.zoom = Math.max(camera.zoom - 0.2f, 0.1f); //Se hace zoom entre 0.1 y 2
                break;
            case Keys.MINUS:
                camera.zoom = Math.min(camera.zoom + 0.2f, 2); //Se hace zoom entre 0.1 y 2
                break;
        }

        keepCameraInBounds();
        camera.update();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("touchdown",screenX+" , "+screenX+" | "+pointer+" | "+button);
        Vector3 unprojection=camera.unproject(new Vector3(screenX,screenY,0));  //Convertimos los píxeles de la pulsación a tiles
        unprojection.x=(float)Math.floor(unprojection.x); //Hacemos Floor para que nos dé el tile exacto, sin decimales.
        unprojection.y=(float)Math.floor(unprojection.y); //Hacemos Floor para que nos dé el tile exacto, sin decimales.
        camera.position.x=unprojection.x; //Establecemos la cámara a la posic.
        camera.position.y=unprojection.y; //Establecemos la cámara a la posic.
        Gdx.app.log("unprojection",unprojection.x+" , "+unprojection.y+" , "+unprojection.z);
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

    private void keepCameraInBounds(){
        if(camera.position.x-(camera.viewportWidth/2)
                *camera.zoom<0){camera.position.x=
                (camera.viewportWidth/2)*camera.zoom;}
        if(camera.position.y-(camera.viewportHeight/2)
                *camera.zoom<0){camera.position.y=(
                        camera.viewportHeight/2)*camera.zoom;}
        if(camera.position.x+(camera.viewportWidth/2)*
                camera.zoom>(int)map.getProperties().get("width")){
            camera.position.x=(int)map.getProperties().get("width")-
                    (camera.viewportWidth/2)
                    *camera.zoom;}if(camera.position.y+
                (camera.viewportHeight/2)*camera.zoom>(int)map.getProperties().get("height"))
                    {camera.position.y=(int)map.getProperties().get("height")-
                            (camera.viewportHeight/2)*camera.zoom;}
    }

}
