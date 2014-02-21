package com.me.mygdxgame;

import java.math.BigInteger;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Planet {
	public int x;
	public int y;
	
	// --- Some data for scale ---
	// mass earth =  5.972*10^24 kg
	// radius earth = 6371 km
	
	public BigInteger mass; // in kg
	public int kmRad;
	public int pixRad;
	
	private Texture texture;
	public Planet(int x, int y){
		this.x = x;
		this.y = y;
		
		Random r = new Random();
		
		// Need to calculate mass in kg and radius in pixels

	}
	
	public Texture getTexture(){
		return texture;
	}
	
}
