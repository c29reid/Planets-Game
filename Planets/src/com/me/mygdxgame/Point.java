package com.me.mygdxgame;

import java.util.Objects;

public class Point {
	public int x;
	public int y;
	
	public Point( int x, int y )
	{
		this.x = x;
		this.y = y;
	}
	
	public int HashCode()
	{
		return Objects.hash(this.x, this.y);
	}
	
	public boolean equals( Object b )
	{
		if ( b == null ) return false;
		if ( !( b instanceof Point ) ) return false;
		Point p = (Point) b;
		return this.x == p.x && this.y == p.y;
		
	}

}
