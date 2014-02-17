package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
	private int w;
	private int h;
	
	int NUM_STARS = 500;
	private ArrayList<Star> stars = new ArrayList<Star>();
	
	//private Pixmap pix;
	private Texture tex;
	
	public Background(int width, int height){
		w = width;
		h = height;
		Pixmap pix = new Pixmap(width, height, Pixmap.Format.RGBA4444);
		pix.setColor(0f, 0f, 0f, 1f);
		pix.fillRectangle(0, 0, w, h);
		
		tex = new Texture(pix);
		
		// Killing the pixmap when done with it
		pix.dispose();
		
		spawnStars(NUM_STARS);
	}
	
	public void update(){
		Star s;
		for (int i=0; i<stars.size(); i++){
			s = stars.get(i);
			s.update();
		}
		
		
	}
	
	public Texture getTexture(){
		return tex;
	}
	
	public void drawStars(SpriteBatch batch){
		// Needs batch.begin() called before this is called
		for (int i=0; i<stars.size(); i++){
			Star s = stars.get(i);
			batch.draw(s.getTexture(), s.x, s.y);
		}
	}
	
	private void spawnStars(int numStars){
		Random r = new Random();
		for (int i=0; i<numStars; i++){
			stars.add(new Star(r.nextInt(w), r.nextInt(h)));
		}
	}
	
}
