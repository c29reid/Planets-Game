package com.me.mygdxgame;

import java.math.BigInteger;
import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Planet {
	private int MINRAD=16;
	private int MAXRAD=32;
	
	public int x;
	public int y;
	
	// --- Some data for scale ---
	// mass earth =  5.972*10^24 kg
	// radius earth = 6371 km
	
	public BigInteger mass; // in kg
	public int kmRad;
	public int pixRad;
	
	// Some base variables of earth for scale
	private int earthPixRad = 16;
	private BigInteger earthKgMass = BigInteger.valueOf(6).multiply(BigInteger.valueOf(10).pow(24));
	
	// Calculating mass per pixel to use to get mass per planet
	private BigInteger massPerPixel = earthKgMass.divide(BigInteger.valueOf((long) (Math.pow(earthPixRad, 2)*Math.PI)));
	private int pixelSizeMeters = 6371/earthPixRad;

	static private Pixmap pix;
	private Texture texture;
	
	public Planet(int x, int y){
		this.x = x;
		this.y = y;
		
		// Calculating mass and radius
		Random r = new Random();
		
		pixRad = r.nextInt(MAXRAD-MINRAD)+MINRAD;
		int pixArea = (int) (Math.pow(pixRad, 2)*Math.PI);	
		mass = massPerPixel.multiply(BigInteger.valueOf(pixArea));
		
		
		// Creating texture
		pix = new Pixmap(MAXRAD*2, MAXRAD*2, Pixmap.Format.RGBA4444);
		pix.setColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1);
		pix.fillCircle(MAXRAD, MAXRAD, pixRad);
		
		texture = new Texture(pix); 
		
	}
	 Texture getTexture(){
		return texture;
	}
	
	public void explode(int x, int y, int radius){
		
	}
}
