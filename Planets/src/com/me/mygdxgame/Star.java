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
	
	public int x;
	public int y;
	
	private Boolean on;
	private int clock;
	private int tick;
	
	public Star(int x, int y){
		this.x = x;
		this.y = y;
		on = true;
		tick = 0;
		
		Random r = new Random();
		clock = r.nextInt(180)+30;
		
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
