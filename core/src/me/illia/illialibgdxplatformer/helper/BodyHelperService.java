package me.illia.illialibgdxplatformer.helper;

import com.badlogic.gdx.physics.box2d.*;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.*;
import static me.illia.illialibgdxplatformer.helper.Consts.PPM;

public class BodyHelperService {
	public static Body createBody(float x, float y, float width, float height, boolean isStatic, World world) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = isStatic ? StaticBody : DynamicBody;
		bodyDef.position.set(x / PPM, y / PPM);
		bodyDef.fixedRotation = true;
		Body body = world.createBody(bodyDef);
		PolygonShape polygon = new PolygonShape();
		polygon.setAsBox(width / 2 / PPM, height / 2 / PPM);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = polygon;
		body.createFixture(fixtureDef);
		polygon.dispose();
		return body;
	}
}
