package es.cenecmalaga.personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Clase que crea el sprite de un jugador que vale para mundo continuo o discreto
 * @author Miguel Páramos
 */
public class Jugador {
    private Sprite sprite; //El sprite del jugador
    private Vector3 posicionTiles; //Posición x e y (z siempre 0) del jugador contando en tiles
    private Vector3 posicionPixels; //Posición x e y (z siempre 0) del jugador contando en pixels
    private OrthographicCamera camara; //Cámara que renderiza al jugado
    private int anchuraMapaPixels; //Anchura del mapa donde nos movemos en pixels
    private int alturaMapaPixels; //Altura del mapa donde nos movemos en pixels
    private int anchuraMapaTiles; //Anchura del mapa donde nos movemos en  tiles
    private int alturaMapaTiles; //Anchura del mapa donde nos movemos en tiles
    private SpriteBatch batch; //Batch donde contengo y dibujo al personaje

    /**
     * Constructor que permite crear un jugador a partir de su
     * género, el mapa donde se mueve, y la cámara que lo renderiza
     * Crea al jugador centrado  en la cámara
     * @param genero false->masculino true->femenino
     * @param cam objeto cámara que se usa en el juego
     * @param mapa mapa que se está renderizando actualmente
     */
    public Jugador(boolean genero, OrthographicCamera cam, TiledMap mapa){
        batch=new SpriteBatch();
        camara=cam;
        anchuraMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
        alturaMapaTiles = ((TiledMapTileLayer) mapa.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa
        anchuraMapaPixels=anchuraMapaTiles*(int)mapa.getProperties().get("width");
        alturaMapaPixels=alturaMapaTiles*(int)mapa.getProperties().get("height");
        this.posicionTiles=new Vector3(camara.position.x,camara.position.y,0);
        posicionPixels=camara.unproject(posicionTiles); //La posición en pixels viene de "desproyectar" o proyectar a la inversa la cámara sobre el mapa, en la posición en la que está el jugador
        if(genero){
            sprite=new Sprite(new Texture("personajes/playerFemale.png"));
        }else{
            sprite=new Sprite(new Texture("personajes/playerMale.png"));
        }

        //Establecemos la posición inicial del Sprite
        sprite.setPosition(posicionPixels.x,posicionPixels.y);

    }

    /**
     * Dibuja el jugador dentro de su batch interno
     */
    public void dibujar(){
        ajustarACamara();
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    //Borra las variables internas que ocupan memoria y no se liberan solas.
    public void dispose(){
        batch.dispose();
    }

    private void ajustarACamara(){
        sprite.setSize(
                ((Gdx.graphics.getWidth()*sprite.getTexture().getWidth())
                        /anchuraMapaPixels)*(1/camara.zoom),
                ((Gdx.graphics.getHeight()*sprite.getTexture().getHeight())
                        /alturaMapaPixels)
                        *(1/camara.zoom));
    }



}
