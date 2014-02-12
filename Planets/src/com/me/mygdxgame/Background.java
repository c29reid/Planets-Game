package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Background {
	private int w;
	private int h;
	
	int NUM_STARS = 500;
	private Array<Star> stars = new Array<Star>();
	
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
	
	private void spawnStars(int numStars){
		for (int i=0; i<numStars; i++){
			stars.add(new Star());
		}
	}
	
}
