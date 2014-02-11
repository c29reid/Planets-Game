package com.me.mygdxgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Game {
	
	private Background bg;
	private Array<Planet> planets = new Array<Planet>();
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	private int width;
	private int height;

	public Game(int width, int height){
		// TODO
		camera = new OrthographicCamera(1, ((float)height)/width);
		batch = new SpriteBatch();
		
		spawnPlanets(5);
	}
	
	/**
	 * Spawns 
	 * @param num Number of planets to spawn on screen
	 */
	public void spawnPlanets(int num){
		// TODO
	}
	
	public void startGame(){
		// TODO
	}
	
	public void mainMenu(){
		// TODO
	}
	
	public void update(){
		// TODO
	}
	
	public void render(){
		// TODO
	}
	
	public void spawnRocket(){
		// TODO
	}
	public void dispose(){
		// TODO
		batch.dispose();
	}
}
