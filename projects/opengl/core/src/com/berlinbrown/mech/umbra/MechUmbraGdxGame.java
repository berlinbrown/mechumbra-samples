/**
 * Berlin Brown - https://www.myberlinaustin.com/ - 2025
 */
package com.berlinbrown.mech.umbra;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.berlinbrown.mech.umbra.screens.MainHUDScreen;

/**
 * Load basic system opengl model full with libgdx.
 * Add and support lights, keyvboard input, multiple objects.
 */
public class MechUmbraGdxGame implements ApplicationListener {

	public Environment lights;
	public PerspectiveCamera cam;
	public ModelBatch modelBatch;
	public Model model;
	public ModelInstance instance;
	public CameraInputController camController;

	protected Stage stage;
	protected Label label;
	protected BitmapFont font;

	protected BitmapFont fontTTF;
	protected Label labelTTF;

	public Model model2;
	public ModelInstance instance2;

	// Render grid
	public Model xaxis;
	public ModelInstance xaxisInstance;

	public Model yaxis;
	public ModelInstance yaxisInstance;

	public Model zaxis;
	public ModelInstance zaxisInstance;

	public MainHUDScreen hudScreen;

	@Override
	public void create() {

		stage = new Stage();
		font = new BitmapFont();
		label = new Label(" ", new Label.LabelStyle(font, Color.WHITE));
		stage.addActor(label);

		// Load TTF
		fontTTF = createFont("fonts/Roboto-Regular.ttf", 14);
		labelTTF = new Label(" ", new Label.LabelStyle(fontTTF, Color.WHITE));
		stage.addActor(labelTTF);

		// Continue to build scene

		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, 1.8f, -0.2f));

		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(4f, 10f, 10f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		// Create instance 1
		final ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				Usage.Position | Usage.Normal);
		instance = new ModelInstance(model);
		instance.transform.translate(0f, 1.0f, 0f);

		// Create instance 2
		model2 = modelBuilder.createBox(5f, 2f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.BLUE)),
				Usage.Position | Usage.Normal);
		instance2 = new ModelInstance(model2);
		instance2.transform.translate(2f, -5.2f, 3f);

		// Create axis grid (three)

		// xaxis
		xaxis = modelBuilder.createBox(24.0f, 0.2f, 0.2f,
				new Material(ColorAttribute.createDiffuse(Color.RED)),
				Usage.Position | Usage.Normal);
		xaxisInstance = new ModelInstance(xaxis);
		xaxisInstance.transform.translate(0f, 0f, 0f);
		//xaxisInstance.transform.scale(0.1f, 1.0f, 1.0f);

		// yaxis
		yaxis = modelBuilder.createBox(0.2f, 24.0f, 0.2f,
				new Material(ColorAttribute.createDiffuse(Color.CYAN)),
				Usage.Position | Usage.Normal);
		yaxisInstance = new ModelInstance(yaxis);
		yaxisInstance.transform.translate(0f, 0f, 0f);

		// zaxis
		zaxis = modelBuilder.createBox(0.2f, 0.2f, 24.0f,
				new Material(ColorAttribute.createDiffuse(Color.GOLD)),
				Usage.Position | Usage.Normal);
		zaxisInstance = new ModelInstance(zaxis);
		zaxisInstance.transform.translate(0f, 0f, 0f);

		// Load input camera controller
		camController = new CameraInputController(cam);

		// Load keyboard input
		final InputProcessor keyboardProcessor = new InputProcessor() {
			@Override public boolean keyDown(final int keycode) {
				System.out.println("Key Pressed: " + keycode);
				if (keycode == 46 )  {
					instance2.transform.translate(0f, 0f, 0.1f);
				} else if (keycode == 34 )  {
					instance2.transform.translate(0f, 0f, -0.1f);
				}
				return true;
			}
			@Override public boolean keyUp(int keycode) { return false; }
			@Override public boolean keyTyped(char character) { return false; }
			@Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
			@Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

			@Override
			public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
			@Override public boolean mouseMoved(int screenX, int screenY) { return false; }
			@Override public boolean scrolled(float amountX, float amountY) { return false; }
		};
		final InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(camController);
		multiplexer.addProcessor(keyboardProcessor);

		Gdx.input.setInputProcessor(multiplexer);

		this.hudScreen = new MainHUDScreen();
		this.hudScreen.show();
	}

	private BitmapFont createFont(final String fontPath, final int size) {
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = size;  // Set font size
			parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
			parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

			BitmapFont font = generator.generateFont(parameter);
			generator.dispose();  // Dispose generator after use
			return font;
	}

	@Override
	public void render() {
		final float delta = Gdx.graphics.getDeltaTime();

		camController.update();
		//instance.transform.translate(0f, -0.2f, 0f);
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelBatch.begin(cam);
		modelBatch.render(instance, lights);
		modelBatch.render(instance2, lights);

		// Continue to render axis
		modelBatch.render(xaxisInstance, lights);
		modelBatch.render(yaxisInstance, lights);
		modelBatch.render(zaxisInstance, lights);
		modelBatch.end();

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.setLength(0);
		stringBuilder.append("FPS: ").append(Gdx.graphics.getFramesPerSecond());
		//label.setText(stringBuilder);
		labelTTF.setText(stringBuilder);
		stage.draw();

		//this.hudScreen.render(delta);
		Gdx.gl.glDepthMask(false);
		hudScreen.render(delta);
		Gdx.gl.glDepthMask(true);
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
		hudScreen.dispose();
	}

	@Override
	public void resize(int width, int height) {

		stage.getViewport().update(width, height, true);
		// Let the HUD screen adjust to the new size
		hudScreen.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		hudScreen.resume();
	}
}
