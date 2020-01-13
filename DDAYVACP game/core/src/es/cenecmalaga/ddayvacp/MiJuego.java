package es.cenecmalaga.ddayvacp;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Clase principal que lanza el juego
 * @author Miguel Páramos
 */
public class MiJuego extends ApplicationAdapter {
	SpriteBatch batch; //Bloque de dibujado, dentro de un batch se imprimen en el mundo todos los sprites que tenga dentro a la vez
	private OrthogonalTiledMapRenderer renderer; //Clase auxiliar para renderizar un mapa.
	private OrthographicCamera camera; //Cámara a través de la que veremos el mundo.
	private static int WIDTH; //Aquí almacenaremos la anchura en tiles
	private static int HEIGHT; //Aquí almacenaremos la altura en tiles
	public static final float unitScale = 1/16f; //Nos servirá para establecer que la pantalla se divide en tiles de 16 pixeles;
	/**
	 * Sirve para inicializar variables, como el mundo, personajes, teclado, sprites...
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth(); //Obtenemos la anchura de nuestra pantalla en pixels
		float h = Gdx.graphics.getHeight(); //Obtenemos la atura de nuestra pantalla en pixels
		TiledMap map = new TmxMapLoader().load("mapas/mapa2.tmx"); //Cargamos el tilemap desde assets
		renderer = new OrthogonalTiledMapRenderer(map, unitScale); //Establecemos el renderizado del mapa dividido en Tiles de 32 dp.
		camera = new OrthographicCamera(); //Declaramos la cámara a través de la que veremos el mundo
		//camera.zoom=0.1f; //Establecemos el zoom de la cámara. 0.1 es más cercano que 1.
		WIDTH = ((TiledMapTileLayer) map.getLayers().get(0)).getWidth(); //Obtenemos desde el mapa el número de tiles de ancho de la 1º Capa
		HEIGHT = ((TiledMapTileLayer) map.getLayers().get(0)).getHeight(); //Obtenemos desde el mapa el número de tiles de alto de la 1º Capa

		Gdx.app.log("Width",Float.toString(WIDTH)); //Sacamos por el log el número de tiles de ancho
		Gdx.app.log("Height",Float.toString(HEIGHT)); //Sacamos por el log el número de tiles de alto

		camera.setToOrtho(false, WIDTH,HEIGHT); //Establecemos la cámara, y le decimos cuanto tiene que ocupar. Por defecto enfoca a la mitad del mundo, al tile central.
		//camera.position.x =WIDTH/2;  //Establecemos la posición x de la cámara en función del número de tiles de la anchura. Jugaremos con esto en clase
		//camera.position.y = HEIGHT/2; //Establecemos la posición x de la cámara en función del número de tiles de la anchura. Jugaremos con esto en clase
		Gdx.app.log("camera x",Float.toString(camera.position.x));
		Gdx.app.log("camera x",Float.toString(camera.position.y));
		camera.update(); //Colocamos la Cámara.
	}

	/**Función que se ejecuta 60 veces por segundo por defecto, y dibuja
	 * en pantalla, calcula acciones, consecuencias, etc. Es el núcleo de nuestro juego
	 */
	@Override
	public void render () {
		//Color de limpieza, evita que cuando se desplaza la cámara
		//se queden dibujos antiguos que ya no deberían estar alli.
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		//limpia el fondo de pantalla con el color indicado
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//indica los elementos que se meten en el batch
		batch.begin();
		//End termina y dibuja.
		batch.end();*/
		renderer.setView(camera); //Establecemos la vista del mundo a través de la cámara.
		renderer.render(); //Renderizamos la vista
	}

	/**
	 * Se llama al cerrar la pantalla de juego, se debe llamar aqui
	 * al dispose de todos los elementos que tengan uno. La mayoría
	 * de los de LibGdx la tiene.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose(); //Destruimos el objeto que renderiza un mapa, para no tener filtraciones de memoria
	}
}
