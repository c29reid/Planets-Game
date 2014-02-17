package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
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
		this.width = width;
		this.height = height;
		// I'm overriding the height and width because textures need to be powers of two until i find a better solution
		width = 512; 
		height = 512;
		camera = new OrthographicCamera(1, ((float)height)/width);
		batch = new SpriteBatch();
		
		bg = new Background(width, height);
		spawnPlanets(5);
		
		startGame();
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
	
	public void tick(){
		update();
		render();
	}
	
	public void update(){
		bg.update();		
		
	}
	
	public void render(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// ------ Background ------
		batch.draw(bg.getTexture(), 0, 0);
		bg.drawStars(batch);
		batch.end();
		
		
	}
	
	public void spawnRocket(){
		// TODO
	}
	public void dispose(){
		// TODO
		batch.dispose();
	}
}
