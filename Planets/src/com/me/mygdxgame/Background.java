package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
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
		// Calculating width/height to be the next higher power of 2.
		if (Math.log10(w)/Math.log10(2) != Math.floor(Math.log10(w)/Math.log10(2))){
			// Case where width isn't already a power of 2
			// w = 2^(x+1)
			Gdx.app.log("Info", Integer.toString((int) (Math.log10(w)/Math.log10(2)))+1);
			w = (int) Math.pow(2, ((int) (Math.log10(w)/Math.log10(2)))+1);
		}
		if (Math.log10(h)/Math.log10(2) != Math.floor(Math.log10(h)/Math.log10(2))){
			// Case where height isn't already a power of 2
			// h = 2^(x+1)
			Gdx.app.log("Info", Integer.toString((int) (Math.log10(w)/Math.log10(2)))+1);
			h = (int) Math.pow(2, ((int) (Math.log10(h)/Math.log10(2)))+1);
		}
		Pixmap pix = new Pixmap(w, h, Pixmap.Format.RGBA4444);
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
		// TODO: Find a better way to draw without copying batch each time this is called
		for (int i=0; i<stars.size(); i++){
			Star s = stars.get(i);
			batch.draw(s.getTexture(), s.x, s.y);
		}
	}
	
	private void spawnStars(int numStars){
		Random r = new Random();
		for (int i=0; i<numStars; i++){
			stars.add(new Star(r.nextInt(w), r.nextInt(h), w, h));
		}
	}
	
}
