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
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.berlinbrown.mech.umbra.screens.MainHUDScreen;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.math.Vector3;

/**
 * Load basic system opengl model full with libgdx.
 * Add and support lights, keyvboard input, multiple objects.
 */
public class MechUmbraGdxGame implements ApplicationListener {

	private Environment lightsEnvironment;
	private PerspectiveCamera cam;
	private ModelBatch modelBatch;
	private Model model;
	private Model triangleModel;
	private ModelInstance instance;
	private CameraInputController camController;

	private Stage stage;
	private Label label;
	private BitmapFont font;

	private BitmapFont fontTTF;
	private Label labelTTF;

	private Model model2;
	private ModelInstance instance2;

	// Render grid
	private Model xaxis;
	private ModelInstance xaxisInstance;

	private Model yaxis;
	private ModelInstance yaxisInstance;

	private Model zaxis;
	private ModelInstance zaxisInstance;

	private MainHUDScreen hudScreen;

	private ModelBatch modelBatchGroup;
	private Model modelGroup;
	private List<ModelInstance> boxInstances;

	private ModelInstance triangleInstance;

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

		lightsEnvironment = new Environment();
		lightsEnvironment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		lightsEnvironment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, 1.8f, -0.2f));

		modelBatch = new ModelBatch();

		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(3f, 10f, 10f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();

		// Create instance 1
		final ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(3f, 3f, 3f,
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

		// Create model triangle player

		// Create a 3D Triangular Pyramid (Tetrahedron)
		// Begin a new model definition.
		modelBuilder.begin();
		// Create a part with triangle primitive type.
		final MeshPartBuilder meshBuilder = modelBuilder.part("prism", GL20.GL_TRIANGLES,
				Usage.Position | Usage.Normal,
				   new Material(ColorAttribute.createDiffuse(Color.ORANGE)));

		// Define bottom triangle vertices (y = 0)
		Vector3 b0 = new Vector3(-1f, 0f, -1f);
		Vector3 b1 = new Vector3( 1f, 0f, -1f);
		Vector3 b2 = new Vector3( 0f, 0f,  1f);

		// Define top triangle vertices (y = 1)
		Vector3 t0 = new Vector3(-1f, 1f, -1f);
		Vector3 t1 = new Vector3( 1f, 1f, -1f);
		Vector3 t2 = new Vector3( 0f, 1f,  1f);

		// Create the bottom face (assuming counterclockwise winding for the front face)
		meshBuilder.triangle(b0, b1, b2);

		// Create the top face with reversed order so its normal faces upward
		meshBuilder.triangle(t2, t1, t0);

		// Side face 1: connects edge from b0 to b1 with corresponding top edge (t0 to t1)
		meshBuilder.triangle(b0, t0, t1);
		meshBuilder.triangle(b0, t1, b1);

		// Side face 2: connects edge from b1 to b2 with corresponding top edge (t1 to t2)
		meshBuilder.triangle(b1, t1, t2);
		meshBuilder.triangle(b1, t2, b2);

		// Side face 3: connects edge from b2 to b0 with corresponding top edge (t2 to t0)
		meshBuilder.triangle(b2, t2, t0);
		meshBuilder.triangle(b2, t0, b0);
		// Complete the model definition
		triangleModel = modelBuilder.end();
		triangleInstance = new ModelInstance(triangleModel);
		triangleInstance.transform.setToTranslation(0f, -3.6f, 0f);

		// Load input camera controller
		camController = new CameraInputController(cam);

		// Load keyboard input
		final InputProcessor keyboardProcessor = new InputProcessor() {
			@Override public boolean keyDown(final int keycode) {
				System.out.println("Key Pressed: " + keycode);
				if (keycode == 46)  {
					System.out.println("Key Pressed: (R)");
					//instance2.transform.translate(0f, 0f, 0.1f);
					triangleInstance.transform.translate(0f, 0f, 0.1f);
				} else if (keycode == 34)  {
					System.out.println("Key Pressed: (F)");
					triangleInstance.transform.translate(0f, 0f, -0.1f);
				} else if (keycode == 48)  {
					triangleInstance.transform.rotate(new Vector3(0, 1, 0), 10f);
				} else if (keycode == 35)  {
					triangleInstance.transform.rotate(new Vector3(0, 1, 0), -10f);
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

		// Create a group of boxes
		final ModelBuilder modelBuilderGroup = new ModelBuilder();
		modelGroup = modelBuilder.createBox(2f, 1f, 2f,
				new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
				Usage.Position | Usage.Normal);

		// Create 40 boxes along the X and Z axes
		boxInstances = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			final ModelInstance box = new ModelInstance(modelGroup);
			//float x = i * 2f;
			float x = 0;
			float z = i * 4.2f;
			box.transform.setToTranslation(x, -8.2f, z);
			boxInstances.add(box);
		}
		// Create another set, a little further along
		for (int i = 0; i < 8; i++) {
			final ModelInstance box = new ModelInstance(modelGroup);
			//float x = i * 2f;
			float x = 6;
			float z = i * 4.2f;
			box.transform.setToTranslation(x, -8.2f, z);
			boxInstances.add(box);
		}


		// Create main hud screen
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
		modelBatch.render(instance, lightsEnvironment);
		modelBatch.render(instance2, lightsEnvironment);

		// Continue to render axis
		modelBatch.render(xaxisInstance, lightsEnvironment);
		modelBatch.render(yaxisInstance, lightsEnvironment);
		modelBatch.render(zaxisInstance, lightsEnvironment);

		// Continue go build group of boxes
		for (final ModelInstance box : boxInstances) {
			modelBatch.render(box, lightsEnvironment);
		}

		// Also render triangle instance
		modelBatch.render(triangleInstance, lightsEnvironment);

		modelBatch.end();

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.setLength(0);
		stringBuilder.append("FPS: ").append(Gdx.graphics.getFramesPerSecond());
		labelTTF.setText(stringBuilder);
		stage.draw();

		Gdx.gl.glDepthMask(false);
		hudScreen.render(delta);
		Gdx.gl.glDepthMask(true);
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
		modelGroup.dispose();
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
