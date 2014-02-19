package com.me.mygdxgame;

import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Star {
	
	int OFF_TIME = 10;
	static Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA4444);
	static Boolean pixInit = false;
	static Texture onTexture;
	static Texture offTexture;
	
	public float x;
	public float y;
	public float dx;
	public int w;
	public int h;
	
	private Boolean on;
	private int clock;
	private int tick;
	
	public Star(float x, float y, int maxX, int maxY){
		this.x = x;
		this.y = y;
		w = maxX;
		h = maxY;
		on = true;
		tick = 0;
		
		Random r = new Random();
		clock = r.nextInt(9360)+120;
		dx = r.nextInt(5)/100f;
		
		createTex();
	}
	
	
	public void update(){
		if (clock-tick <= OFF_TIME){
			on = false;
		}
		if (tick == 0){
			on = true;
		}
		tick = (tick+1)%clock;
		x += dx;
		x = x % w;
	}
	
	public Texture getTexture(){
		if (on){
			return onTexture;
		}
		return offTexture;
	}
	
	private void setPixOn(){
		pix.setColor(1, 1, 1, 1);
		pix.fillRectangle(0, 0, 1, 1);
	}
	private void setPixOff(){
		pix.setColor(0, 0, 0, 1);
		pix.fillRectangle(0, 0, 1, 1);
	}
	
	private void createTex(){
		// Creating the star texture
		if (pixInit == false){
			setPixOn();
			onTexture = new Texture(pix);
			setPixOff();
			offTexture = new Texture(pix);
			pixInit = true;
		}
	}
}
