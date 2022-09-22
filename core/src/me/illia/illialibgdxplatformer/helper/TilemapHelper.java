package me.illia.illialibgdxplatformer.helper;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import me.illia.illialibgdxplatformer.objects.player.PlayerEntity;
import me.illia.illialibgdxplatformer.screens.GameScreen;

import static me.illia.illialibgdxplatformer.helper.Consts.PPM;

public class TilemapHelper {
	private TiledMap tiledMap;
	private GameScreen screen;

	public TilemapHelper(GameScreen screen) {
		this.screen = screen;
	}

	public OrthogonalTiledMapRenderer setupLevel() {
		tiledMap = new TmxMapLoader().load("levels/tutorial-level.tmx");
		parseLevelObjects(tiledMap.getLayers().get("objects").getObjects());
		return new OrthogonalTiledMapRenderer(tiledMap);
	}
	private void parseLevelObjects(MapObjects objects) {
		for (MapObject object : objects) {
			if (object instanceof PolygonMapObject) {
				createStaticBody((PolygonMapObject)object);
			}
			if (object instanceof RectangleMapObject) {
				Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
				String rectangleName = object.getName();
				if (rectangleName.equals("player")) {
					Body body = BodyHelperService.createBody(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2, rectangle.getWidth(), rectangle.getHeight(), false, screen.getWorld());
					screen.setPlayerEntity(new PlayerEntity(rectangle.getWidth(), rectangle.getHeight(), body));
				}
			}
		}
	}
	private void createStaticBody(PolygonMapObject polygon) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		Body body = screen.getWorld().createBody(bodyDef);
		Shape shape = createPolygonShape(polygon);
		body.createFixture(shape, 1000);
		shape.dispose();
	}
	private Shape createPolygonShape(PolygonMapObject polygon) {
		float[] vertices = polygon.getPolygon().getTransformedVertices();
		Vector2[] worldV = new Vector2[vertices.length / 2];
		for (int i = 0; i < vertices.length / 2; i++) {
			Vector2 current = new Vector2(vertices[i * 2] / PPM, vertices[i * 2 + 1] / PPM);
			worldV[i] = current;
		}
		PolygonShape polygonR = new PolygonShape();
		polygonR.set(worldV);
		return polygonR;
	}
}
