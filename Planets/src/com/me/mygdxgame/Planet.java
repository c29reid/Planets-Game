package com.me.mygdxgame;

import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Planet {
	Boolean DEBUG = true;
	private int TEXTURESIZE = 128; // needs to be power of 2 -- size of pixmap texture side length
	private int MINRAD=32; 
	private int MAXRAD=33;    
	
	public int x;
	public int y;
	
	
	// --- Some data for scale ---
	// mass earth =  5.972*10^24 kg
	// radius earth = 6371 km
	
	public BigInteger mass; // in kg
	public int kmRad;
	public int planetPixRad;
	
	// Some base variables of earth for scale
	private int earthPixRad = 8;
	private BigInteger earthKgMass = BigInteger.valueOf(6).multiply(BigInteger.valueOf(10).pow(24));
	
	// Calculating mass per pixel to use to get mass per planet
	private BigInteger massPerPixel = earthKgMass.divide(BigInteger.valueOf((long) (Math.pow(earthPixRad, 2)*Math.PI)));
	private int pixelSizeMeters = 6371000/earthPixRad;

	public Pixmap pix;
	private Texture texture;
	
	public Planet(int x, int y){
		this.x = x;
		this.y = y;
		
		// Calculating mass and radius
		Random r = new Random();
		planetPixRad = r.nextInt(MAXRAD-MINRAD)+MINRAD;
		int pixArea = (int) (Math.pow(planetPixRad, 2)*Math.PI);	
		mass = massPerPixel.multiply(BigInteger.valueOf(pixArea));
		
		
		// Creating texture
		pix = new Pixmap(TEXTURESIZE,TEXTURESIZE, Pixmap.Format.RGBA4444);
		pix.setColor(r.nextFloat(), r.nextFloat(), r.nextFloat(), 1);
		pix.fillCircle(TEXTURESIZE/2, TEXTURESIZE/2, planetPixRad);
		texture = new Texture(pix); 
	}
	
	public Texture getTexture(){
		return texture;
	}
	

	public Vector<Double> gForce(int centerX, int centerY){
		
		Vector<Double> force = new Vector<Double>();
		
		// Calculating the actual force of gravity
		// F = G*m1*m2/r^2
		double G = 6.674; // Will divide the mass by 10^11 later for G
		double tempMass = this.mass.divide(BigInteger.valueOf(100000000000l)).doubleValue(); 
		
		// Calculating dx and dy in meters
		double dx = x+TEXTURESIZE/2 - centerX;
		double dy = y+TEXTURESIZE/2 - centerY;
		dx *= pixelSizeMeters;
		dy *= pixelSizeMeters;		
		double r = Math.pow(dx, 2)+Math.pow(dy, 2);
		
		Gdx.app.log("Dist", Double.toString(r));
		if (r == 0){ 
			// Shouldn't be possible for r=0 with collision but just in case
			r = 0.0000000001d;
		}
		double accel = tempMass*G/r;
		
		// Dividing the speed to be in scale of pixels
		Gdx.app.log("Accel", Double.toString(accel));
		
		// Splitting up the dx and dy components for the vector
		double angle = Math.atan2(dy, dx); 
		
		double dxFinal = accel*Math.cos(angle);
		double dyFinal = accel*Math.sin(angle);	
		force.add(dxFinal);
		force.add(dyFinal);
		return force;
	}
	
	public void explode(int x, int y, int pixRadius){
		// Set the pixmap to not blend
		pix.setBlending(Pixmap.Blending.None);
		pix.setColor(1, 1, 1, 0);
		pix.drawCircle(x-this.x, y-this.y, pixRadius);
		texture = new Texture(pix);
		pix.setBlending(Pixmap.Blending.SourceOver);
	}
	
	public void kill(){
	}
	
	/**
	 * destroy - Dispose of objects that would cause memory leaks
	 */
	public void destroy(){
		pix.dispose();
	}
	
	public int getWidth(){
		return TEXTURESIZE/2;
	}
}
