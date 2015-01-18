package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Asteroid extends  Rocket{
	// Asteroids are similar to rockets except if they collide with a rocket it scatters into smaller pieces
	static private Random r = new Random();
	
	private Texture tex;
	private TextureRegion region;
	private int rotation;
	private int width;
	private int height;
	public int rad;
	public int masskg;
	
	public Asteroid(int x, int y, Vector<Double> initThrust,
			ArrayList<Planet> initPlanets) {
		super(x, y, initThrust, initPlanets);
		// TODO Auto-generated constructor stub
		
		// Load texture
		loadTexture();

		
		// Set up initial velocity
		if (initThrust == null){
			randomizeVelocity();
		}
	}
	
	public void randomizeVelocity(){
		rotation = r.nextInt(361);
		Vector<Double> thrust = new Vector<Double>();
		boolean sign1 = r.nextBoolean();
		boolean sign2 = r.nextBoolean();
		double dx = r.nextInt(2)*Planet.PIXELSIZEMETERS;               // Move from 1-10 pixels/second
		double dy = r.nextInt(2)*Planet.PIXELSIZEMETERS; 
		
		if (sign1){dx = dx * -1;}
		if (sign2){dy = dy*-1;}
		
		thrust.add(0d);
		thrust.add(0d);
		super.setThrust(thrust);
	}
	
	public Texture getTexture(){
		return tex;
		
	}
	
	public TextureRegion getRegion() { return region;} 
	
	public int getRotation() {
		return rotation;
	}

	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void loadTexture(){
		tex = new Texture(Gdx.files.internal("data/64asteroid1.png"));  // Should look at using atlas for textures
		rad = 24;
		width = 32;
		height = 32;
		this.masskg =20000; 
		super.setMassKg(this.masskg);
		region = new TextureRegion(tex);
	}
	

}
