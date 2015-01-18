package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

/**
 * 
 * @author Christopher Reid
 *
 * TODO: 
 * 	- fix window size being inconsistent and make planet spawns more centered
 * 	- Make texture for asteroid be randomly rotated on spawn
 *		- could be done either through separate .png files or dynamically with libgdx
 */

public class Game {

	private Background bg;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	private SpriteBatch batch;
	private OrthographicCamera camera;

	private int width;
	private int height;
	private float asteroidProb = 0.03f;

	private Boolean checkPressed = true;
	private InputHandle inputHandler;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		// Creating the camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		// Hack to make the camera co-ordinate system consistent with the input
		// co-ords
		camera.setToOrtho(true, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.update();

		// Giving the new camera to the sprite batch
		batch = new SpriteBatch(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.setProjectionMatrix(camera.combined);

		bg = new Background(width, height);
		createPlanets(1);

		inputHandler = new InputHandle(this);

		startGame();
	}

	/**
	 * Spawns
	 * 
	 * @param num
	 *            Number of planets to spawn on screen
	 */

	public void createPlanets(int num) {
		Random r = new Random();
		int tempx;
		int tempY;
		/*for (int i = 0; i < num; i++) {
			planets.add(new Planet(r.nextInt(width - Planet.MAXRAD)
					+ Planet.MINRAD, r.nextInt(height - Planet.MAXRAD)
					+ Planet.MINRAD));
			// TODO: Check for collision on planet spawning
		}*/
		planets.add( new Planet(width/2, height/2) );
	}

	public void startGame() {
		// TODO

	}

	public void tick() {
		update();
		render();
	}

	public void update() {
		bg.update();
		
		updateList(rockets);
		updateList(asteroids);
		//updateList(planets);
		//updateRockets();
		//updateAsteroids();
		spawnRandomAsteroid();
	}
	
	public <T extends Rocket, Planet, Asteroid> void updateList(ArrayList<T> list){
		Iterator<T> iter = list.iterator();
		while (iter.hasNext()) {
			T r = iter.next();
			r.update();
			if (r.isDead()) {
				iter.remove();
			}
		}
	}

	public void render() {

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// Background drawing
		batch.draw(bg.getTexture(), 0, 0);

		bg.drawStars(batch);
		// Planets drawing
		for (int i = 0; i < planets.size(); i++) {
			Planet p = planets.get(i);
			batch.draw(p.getTexture(), p.x, p.y);
		}
		for (int i = 0; i < rockets.size(); i++) {
			Rocket r = rockets.get(i);
			batch.draw(r.getTexture(), r.getX(), r.getY());
		}
		for (int i = 0; i < asteroids.size(); i++) {
			Asteroid r = asteroids.get(i);
			batch.draw(r.getRegion(), r.getX(), r.getY(), r.getWidth() / .2f,
					r.getHeight() / .2f, r.getWidth(), r.getHeight(), 1, 1,
					r.getRotation(), false);
		}
		batch.end();
	}

	public void spawnRocket(int x, int y, int x2, int y2) {
		// Thrust will be a vector with a dx and dy
		Vector<Double> thrust = new Vector<Double>();
		int factor = 30;
		double dx = (x2 - x) / factor;

		double dy = (y2 - y) / factor;
		thrust.add(0, dx);
		thrust.add(1, dy);
		// rockets.add(new Rocket(x, y, thrust, planets));
		// Gdx.app.log("INFO", "spawned Rocket");
		rockets.add(new Rocket(x, y, thrust, planets));
		Gdx.app.log("INFO", "spawned Rocket");
	}

	public void spawnRandomAsteroid() {
		Random r = new Random();
		if (r.nextFloat() > asteroidProb) {
			return;
		}

		int x = r.nextInt(width);
		int y = r.nextInt(height);
		Vector<Double> thrust = new Vector<Double>();
		//asteroids.add(new Asteroid(x, y, thrust, planets));
	}

	public void dispose() {
		batch.dispose();
	}

}
