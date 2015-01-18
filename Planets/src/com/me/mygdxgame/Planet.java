package com.me.mygdxgame;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Planet {
	
	static int MINRAD=50; 
	static int MAXRAD=64;  
	private boolean DEBUG = true;
	private static int TEXTURESIZE = 128; // needs to be power of 2 -- size of pixmap texture side length  
	
	public int x;
	public int y;
	
	
	// --- Some data for scale ---
	// mass earth =  5.972*10^24 kg
	// radius earth = 6371 km
	
	public BigInteger mass; // in kg
	public int kmRad;
	public int planetPixRad;
	
	private Random r;
	
	
	// Some base variables of earth for scale
	private static int earthPixRad = 8;
	private BigInteger earthKgMass = BigInteger.valueOf(6).multiply(BigInteger.valueOf(10).pow(24));
	private static BigInteger earthDensityKgPerMSquared = BigInteger.valueOf( 50 ); 
	private float ticksPerMove = 20;
	private int clock = 0;
	
	// Calculating mass per pixel to use to get mass per planet
	private BigInteger massPerPixel = earthKgMass.divide(BigInteger.valueOf((long) (Math.pow(earthPixRad, 2)*Math.PI)));
	private static int pixelSizeMeters = 637100000/earthPixRad; // how many meters are in a pixel
	static int PIXELSIZEMETERS = pixelSizeMeters;

	
	public Pixmap pix;
	private Texture texture;
	
	public Planet(int x, int y){
		this.x = x;
		this.y = y;
		r = new Random();
		// Calculating mass and radius
		planetPixRad = r.nextInt(MAXRAD-MINRAD)+MINRAD;
		int pixArea = (int) (Math.pow(planetPixRad, 2)*Math.PI);	
		mass = massPerPixel.multiply(BigInteger.valueOf(pixArea));
		
		
		// Creating texture
		pix = new Pixmap(TEXTURESIZE,TEXTURESIZE, Pixmap.Format.RGBA4444);
		initTexture();
		genetateTerrain();
		randomizeBrightness();
		texture = new Texture(pix); 
	}
	
	private void genetateTerrain( )
	{
		// Pick a few points on the planet, then 
		
		int x2,y2;
		int numRegions = r.nextInt()%3+3;
		for ( int i=0; i<numRegions; i++ )
		{
			Color c =  randomizeGoodColor();
			x2 = r.nextInt(2*planetPixRad)-planetPixRad+TEXTURESIZE/2;
			y2 = r.nextInt(2*planetPixRad)-planetPixRad+TEXTURESIZE/2;
			generateTerrainInternal( new Point( x2, y2 ), new ArrayList< Point >(), 1.0f,  c );
		}
		
	}
	
	private void generateTerrainInternal( Point p, ArrayList< Point > visited, float probGenerate, Color c )
	{
		float prob = r.nextFloat();
		float nextPScale = 0.97f;
		
		Point thisPos = p;
		
		Color col = new Color();
		Color.rgb888ToColor( col, pix.getPixel(p.x, p.y) );
		
		if ( prob > probGenerate )
		{
			pix.setColor( (c.r+col.r)/2.0f, (c.g+col.g)/2.0f, (c.b+col.b)/2.0f, 1.0f);
			pix.drawPixel( p.x, p.y );
			pix.setColor( c ); 
			return;
		}

		
		if ( visited.indexOf( thisPos ) > -1 || ( col.a <= 0.001f && col.b <= 0.001f && col.g <= 0.001f ) )
		{
			return;
		}
		
		pix.drawPixel( p.x, p.y, c.rgba8888(c) );
		
		visited.add( thisPos );
		
		generateTerrainInternal( new Point( p.x-1, p.y ), visited, nextPScale*probGenerate, c );
		generateTerrainInternal( new Point( p.x+1, p.y ), visited, nextPScale*probGenerate, c );
		generateTerrainInternal( new Point( p.x-1, p.y-1 ), visited, nextPScale*probGenerate, c );
		generateTerrainInternal( new Point( p.x+1, p.y-1 ), visited, nextPScale*probGenerate, c );
	}
	
	private void randomizeBrightness()
	{
		int x, y;
		x = TEXTURESIZE/2;
		y = TEXTURESIZE/2;
		
		// u^2 + v^2 = r^2
		for (int u=-planetPixRad; u<=planetPixRad; u++)
		{
			for ( int v=-planetPixRad; v<=planetPixRad; v++)
			{
				float b;
				int colourInt = pix.getPixel(v+x, u+y);
				if ( u*u + v*v > planetPixRad*planetPixRad )
				{
					b = 0.0f;
				}
				else
				{
					b = (float) ( ( Math.cos(Math.PI*(u*u+v*v)/(planetPixRad*planetPixRad) ) + 1 ) / 2.0f )*0.89f + r.nextFloat()*0.11f;
				}
				Color c = new Color();
				Color.rgba8888ToColor( c, colourInt );
				pix.setColor( c.r*b, c.g*b, c.b*b, c.a );
				pix.drawPixel(x+v, y+u);
			}
		}
	}
	
	public int getCentreX()
	{
		return this.x+TEXTURESIZE/2;
	}
	public int getCentreY()
	{
		return this.y+TEXTURESIZE/2;
	}
	
	private Color randomizeGoodColor()
	{
		int n = 3;
		
		float R=1f; // I am 99% sure this is not working properly, but it looks too good to fix
		float G=1f;
		float B=1f;
		
		for (int i=0; i<n; i++)
		{
			R = (R + r.nextFloat())/2.0f;
			G = (G + r.nextFloat())/2.0f;
			B = (B + r.nextFloat())/2.0f;
		}
		
		
		pix.setColor(R, G, B, 1);
		return new Color( R, G, B, 1 );
	}
	
	private void initTexture()
	{
		randomizeGoodColor();
		pix.fillCircle(TEXTURESIZE/2, TEXTURESIZE/2, planetPixRad);		
	}
	
	public Texture getTexture(){
		return texture;
	}
	
	public void update(){
		if (clock%ticksPerMove == ticksPerMove-1){
			x += 1;
		}
		clock = (1 + clock)%60;
	}
	
	
	public boolean basicCollisionCheck(Rocket r){
		double dist = Math.sqrt(Math.pow(r.getX()-x, 2)+Math.pow(r.getY()-y, 2));
		if (dist <= r.rad+planetPixRad){
			return true;
		}
			
		return false;
	}
	

	// get the acceleration from the centre of another object
	public Vector<Double> gForce(int centerX, int centerY){
		
		Vector<Double> force = new Vector<Double>();
		
		// Calculating the actual force of gravity
		// F = G*m1*m2/r^2
		double G = 6.674; 
		// Divide the mass by 10^11 for G calculation
		// not used anymore
		double tempMass = this.mass.divide(BigInteger.valueOf(100000000000l)).doubleValue(); 
		
		// Calculating dx and dy in meters
		double dx = x+TEXTURESIZE/2 - centerX;
		double dy = y+TEXTURESIZE/2 - centerY;
		dx *= pixelSizeMeters;
		dy *= pixelSizeMeters;		
		double r = Math.pow((int) dx, 2)+Math.pow((int) dy, 2);
		
		if (r == 0){ 
			// Shouldn't be possible for r=0 with collision but just in case
			r = 0.0000000001d;
		}
		
		double accel = getMass(r).divide(BigInteger.valueOf(100000000000l)).doubleValue()*6.674/Math.sqrt(r);
		
		
		// Dividing the speed to be in scale of pixels
		
		// Splitting up the dx and dy components for the vector
		double angle = Math.atan2(dy, dx); 
		
		double dxFinal = accel*Math.cos(angle);
		double dyFinal = accel*Math.sin(angle);	
		force.add(dxFinal);
		force.add(dyFinal);
		return force;
	}
	
	public void explode(double x, double y, int pixRadius){
		// Set the pixmap to not blend
		pix.setBlending(Pixmap.Blending.None);
		pix.setColor(1, 1, 1, 0);
		pix.fillCircle((int) (x-this.x), (int) (this.y-y), pixRadius);
		texture = new Texture(pix);
		pix.setBlending(Pixmap.Blending.SourceOver);
	}
	
	public BigInteger getMass( double distM )
	{
		return earthDensityKgPerMSquared.multiply( BigInteger.valueOf( (long) (Math.PI * Math.pow( distM, 2) ) ) );
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
