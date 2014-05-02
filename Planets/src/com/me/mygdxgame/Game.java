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

public class Game {
	
	private Background bg;
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	private ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private int width;
	private int height;
	
	private Boolean checkPressed = true;
	private InputHandle inputHandler;
	
	
	public Game(int width, int height){
		this.width = width;
		this.height = height;
		// Creating the camera
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Hack to make the camera co-ordinate system consistent with the input co-ords
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		
		batch = new SpriteBatch();
		
		bg = new Background(width, height);
		createPlanets(1);
		
		inputHandler = new InputHandle(this);
		
		startGame();
	}
	
	/**
	 * Spawns 
	 * @param num Number of planets to spawn on screen
	 */

	public void createPlanets(int num){
		Random r = new Random();
		int tempx;
		int tempY;
		for (int i=0; i< num; i++){
			planets.add(new Planet(r.nextInt(width), r.nextInt(height)));
			// TODO: Check for collision on planet spawning
		}
	}
	
	public void startGame(){
		// TODO
		
	}
	
	public void tick(){
		update();
		render();
	}
	
	public void update(){
		bg.update();	
		updateRockets();
	}
	
	public void updateRockets(){
		Iterator<Rocket> rocketIter = rockets.iterator();
		
		while (rocketIter.hasNext()){
			Rocket r = rocketIter.next();
			r.update();
			if (r.isDead()){
				rockets.remove(r);
			}
		}
	}
	
	public void render(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// ------ Background ------
		batch.draw(bg.getTexture(), 0, 0);
		bg.drawStars(batch);
		// ------ Planets ------
		for (int i=0; i<planets.size(); i++){
			Planet p = planets.get(i);
			batch.draw(p.getTexture(), p.x, p.y);
		}
		for (int i=0; i<rockets.size(); i++){
			Rocket r = rockets.get(i);
			batch.draw(r.getTexture(), r.getX(), r.getY());
		}
		batch.end();
		
	}
	

	
	public void spawnRocket(int x, int y, int x2, int y2){
		// Thrust will be a vector with a dx and dy
		Vector<Double> thrust = new Vector<Double>();
		int factor = 60;
		double dx = (x2-x)/factor;
		
		double dy = (y2-y)/factor;
		thrust.add(0, dx);
		thrust.add(1, dy);
		rockets.add(new Rocket(x, height-y, thrust, planets));
		Gdx.app.log("INFO", "spawned Rocket");
	}
	
	public void dispose(){
		batch.dispose();
	}
	
}
