package com.berlinbrown.mech.umbra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Renderer
 */
public class MechUmbraGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	ShapeRenderer shapeRenderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		final SimpleInputProcessor inputProcessor = new SimpleInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		batch.begin();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		//shapeRenderer.rect(SimpleInputProcessor.x, SimpleInputProcessor.y, 50, 50);
		shapeRenderer.triangle(SimpleInputProcessor.x, SimpleInputProcessor.y,
				SimpleInputProcessor.x+25,
				SimpleInputProcessor.y+75,
				SimpleInputProcessor.x+50,
				SimpleInputProcessor.y+50);

		shapeRenderer.end();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
