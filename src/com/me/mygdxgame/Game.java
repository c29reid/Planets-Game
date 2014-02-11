package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Game {
	
	private Background bg;
	private Array<Planet> planets = new Array<Planet>();
	
	private SpriteBatch batch;
	private Texture texture;
	
	private int width;
	private int height;

	public void Game(int width, int height){
		// TODO
		
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
		texture.dispose();
	}
}
