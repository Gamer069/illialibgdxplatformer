package me.illia.illialibgdxplatformer.objects.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import static me.illia.illialibgdxplatformer.helper.Consts.PPM;

public class PlayerEntity extends GameEntity {
	public PlayerEntity(float width, float height, Body body) {
		super(width, height, body);
		this.speed = 4f;
	}

	@Override
	public void update() {
		x = body.getPosition().x * PPM;
		y = body.getPosition().y * PPM;
	}

	@Override
	public void render(SpriteBatch batch) {

	}
}
