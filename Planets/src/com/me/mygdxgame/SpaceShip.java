package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpaceShip{

	private Texture texture;
	private TextureRegion region;
	int x;
	int y;
	
	SpaceShip()
	{
	}
	public void loadTexture(){
		texture = new Texture(Gdx.files.internal("data/SpaceShip1.png"));
		region = new TextureRegion(texture);
	}
	Texture getTexture()
	{
		return texture;
	}
}
