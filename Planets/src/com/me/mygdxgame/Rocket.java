package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Rocket {
	private int LIFE = 1024;
	
	private double x;
	private double y;
	private double oldx;
	private double oldy;
	private int w;
	private int h;
	private int timeAlive; 
	private boolean alive;
	
	public int massKg;
	public Vector<Double> thrustPixelsPerTick;
	
	private Texture texture;

	public int rad;
	static Pixmap pix;
	static ArrayList<Planet> planets;
	
	/**
	 * 
	 * @param x - x position of starting rocket in pixels
	 * @param y - y position of starting rocket in pixels
	 * @param initThrust - 2d vector of starting thrust values (velocity is in pixels/tick)
	 * @param initPlanets - Reference to list of existent planets (used for calculating gravitational pulls)
	 */
	public Rocket(int x, int y, Vector<Double> initThrust, ArrayList<Planet> initPlanets){
		thrustPixelsPerTick = initThrust;
		this.x = x;
		this.y = y;
		oldx = x;
		oldy = y;
		w = 8;
		h = 8;
		massKg = 1000; // kg
		alive = true;
		
		// Checking if the static pixmap has been initialized or planet list reference have been initialized
		if (pix == null){
			pix = new Pixmap(w, h, Pixmap.Format.RGBA4444);
		}
		if (planets == null){
			planets = initPlanets;
		}
		timeAlive = 0;
		createTexture();
	}
	
	public void update(){
		
		calcNewThrust();
		thrustMove();
		checkTooOld();
		explodeIfCollided();
		
		timeAlive++;
	}
	
	private void calcNewThrust(){
		Vector<Double> force;
		// Calculate new thrust from other planets.
		for (int i=0; i<planets.size(); i++){
			Planet p = planets.get(i);
			
			// Get the gravitational pull the current planet
			force = p.gForce((int)x+w/2, (int)y+h/2);
			
			// Calcuating gravitational forces 
			double dx = massKg*force.get(0)/Math.pow(120, 2);
			double dy = massKg*force.get(1)/Math.pow(120, 2);
			
			// Changing the movement per tick
			thrustPixelsPerTick.set(0, thrustPixelsPerTick.get(0)+dx); 
			thrustPixelsPerTick.set(1, thrustPixelsPerTick.get(1)+dy);
		}
	}
	
	private void thrustMove(){
		//Gdx.app.log("INFO - Start", Double.toString(x) + " " + Double.toString(y));
		oldx = x;
		oldy = y;
		x += thrustPixelsPerTick.get(0);
		y += thrustPixelsPerTick.get(1);
		//Gdx.app.log("INFO - End", Double.toString(x) + " " + Double.toString(y));
	}
	
	private void createTexture(){
		pix.setColor(1, 0, 0, 1);
		pix.fillRectangle(0, 0, w, h); // Temporary texture
		texture = new Texture(pix);
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	/**
	 * hasCollided - Check to see if a rocket has collided with a planet 
	 * @param p - Planet to check collision
	 * @return true if collided false otherwise
	 */
	private boolean hasCollided(Planet p){
		// First if the traveled vector line has collided O(1)
		// Then use pixel-collision detection
		boolean collided = false;
		// Use vector projection to calculate distance from line to circle
		float dx = (float)(x - oldx);
		float dy = (float)(y - oldy);
		
		Vector2 startV = new Vector2( (float) oldx, (float)oldy );
		
		
		
		alive = !collided;
		return collided;
		
	}
	public void explodeIfCollided(){
		// Check for all planets collided with
		for (int i=0; i<planets.size(); i++){
			Planet p = planets.get(i);
			
			if (hasCollided(p)){
				p.explode(x, y, 10);
			}
		}
	}
	
	public void checkTooOld(){
		if (timeAlive > LIFE){
			alive = false;
		}
	}
	
	
	// --- Accessors and Modifiers ---
	
	public int getX(){
		return (int) this.x;
	}
	
	public int getY(){
		return (int) this.y;
	}
	
	public int getMassKg() {
		return massKg;
	}

	public void setMassKg(int massKg) {
		this.massKg = massKg;
	}

	public boolean isDead(){
		return alive==false;
	}
	
	public void setThrust(Vector<Double> initThrust){
		thrustPixelsPerTick = initThrust;
	}
}
