package com.me.mygdxgame;

import java.util.Vector;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlanetsGame implements ApplicationListener {
	private Sprite sprite;
	private Game game;
	
	@Override
	public void create() {		

		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		game = new Game(w, h);
		
	}

	@Override
	public void dispose() {	
		game.dispose();
	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		game.tick();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		dispose(); 
		create();
	}
	
}
