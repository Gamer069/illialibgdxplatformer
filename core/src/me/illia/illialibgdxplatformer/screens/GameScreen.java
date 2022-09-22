package me.illia.illialibgdxplatformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import me.illia.illialibgdxplatformer.helper.TilemapHelper;
import me.illia.illialibgdxplatformer.objects.player.PlayerEntity;

import static me.illia.illialibgdxplatformer.helper.Consts.PPM;

public class GameScreen extends ScreenAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthogonalTiledMapRenderer tiledMapRenderer;
	private TilemapHelper tilemapHelper;
	private PlayerEntity playerEntity;
	public GameScreen(OrthographicCamera camera) {
		this.camera = camera;
		this.batch = new SpriteBatch();
		this.world = new World(new Vector2(0, 0), false);
		this.debugRenderer = new Box2DDebugRenderer();
		this.tilemapHelper = new TilemapHelper(this);
		this.tiledMapRenderer = tilemapHelper.setupLevel();
	}

	@Override
	public void render(float delta) {
		this.update();
		tiledMapRenderer.render();
		batch.begin();

		batch.end();
		debugRenderer.render(world, camera.combined.scl(PPM));
	}

	private void update() {
		world.step( 1 / 60f, 6, 2);
		cameraUpdate();
		batch.setProjectionMatrix(camera.combined);
		tiledMapRenderer.setView(camera);
		if (Gdx.input.isKeyPressed(Input.Keys.F12)) {
			Gdx.app.exit();
		}
	}
	private void cameraUpdate() {
		camera.position.set(new Vector3(0, 0, 0));
		camera.update();
	}

	public World getWorld() {
		return world;
	}
	public void setPlayerEntity(PlayerEntity player) {
		this.playerEntity = player;
	}
}
