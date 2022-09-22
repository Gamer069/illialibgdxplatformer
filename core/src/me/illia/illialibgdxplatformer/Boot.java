package me.illia.illialibgdxplatformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import me.illia.illialibgdxplatformer.screens.GameScreen;

public class Boot extends Game {
	public static Boot INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera camera;

	public Boot() {
		INSTANCE = this;
	}

	@Override
	public void create() {
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, widthScreen, heightScreen);
		this.setScreen(new GameScreen(camera));
	}
}
