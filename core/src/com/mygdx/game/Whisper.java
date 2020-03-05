package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Whisper extends Game {
	
	
	private static final String TITLE = "The Whisper v0.1.0";
	private OrthographicCamera camera;
	
	@Override
	public void create() {
		Gdx.app.log(TITLE, "create()");
		camera = new OrthographicCamera();
	    camera.setToOrtho(false, 1800, 480);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	
	/*
	Load images in batches through each floor. 
	Dispose images when leaving the floor.
	*/
	
	@Override
	public void dispose() {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}
}
