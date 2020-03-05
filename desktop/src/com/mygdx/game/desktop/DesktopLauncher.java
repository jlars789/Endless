package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Whisper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "The Whisper";
		config.useGL30 = true;
	    config.width = 1440;
	    config.height = 810;
	    config.foregroundFPS = 60;
		new LwjglApplication(new Whisper(), config);
	}
}
