package es.cenecmalaga.entrada;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

public abstract class EscuchadorTeclado implements InputProcessor {
    protected OrthographicCamera camera;
    protected TiledMap map;

    public EscuchadorTeclado(OrthographicCamera c,TiledMap m){
        camera=c;
        map=m;
    }

    protected void keepCameraInBounds(){
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
