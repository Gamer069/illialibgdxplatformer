package me.illia.illialibgdxplatformer;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowIcon("player.png");
		config.setIdleFPS(60);
		config.setResizable(false);
		config.setTitle("Illia's LibGDX Platformer :)");
		config.setInitialBackgroundColor(Color.VIOLET);
		config.setWindowedMode(960, 640);
		new Lwjgl3Application(new Boot(), config);
	}
}
