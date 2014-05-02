package com.me.mygdxgame;

import java.util.Hashtable;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandle implements InputProcessor, ApplicationListener {
	private Game game;
	
	private class PointTouch{
		int x;
		int y;
		
		public PointTouch(int xin, int yin){
			x = xin;
			y = yin;
		}
	}
	
	private Hashtable<Integer, PointTouch> inputPoints = new Hashtable<Integer, PointTouch>();
	
	public InputHandle(Game game){
		this.game = game;
		Gdx.input.setInputProcessor(this);
	}
	/**
	 * handleInput - Function that checks the input and properly calls functions
	 * to handle input events
	 */
	
	public void handleInput(){
		
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		inputPoints.put(pointer, new PointTouch(screenX, screenY));
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		PointTouch point = inputPoints.get(pointer);
		inputPoints.remove(pointer);
		game.spawnRocket(point.x, point.y, screenX, screenY);
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY){
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
