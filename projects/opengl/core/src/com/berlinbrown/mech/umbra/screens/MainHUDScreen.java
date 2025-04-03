package com.berlinbrown.mech.umbra.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainHUDScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    protected BitmapFont fontTTF;
    protected Label labelTTF;
    @Override
    public void show() {

        // Load TTF
        // Create a stage with a screen viewport
        stage = new Stage(new ScreenViewport());
        fontTTF = createFont("fonts/Roboto-Regular.ttf", 14);
        labelTTF = new Label(" ", new Label.LabelStyle(fontTTF, Color.WHITE));
        stage.addActor(labelTTF);
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
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

        // Position the label in the upper-left corner
        labelTTF.setPosition(10, Gdx.graphics.getHeight() - 20);
        labelTTF.setText("[Umbra Home] FPS: " + Gdx.graphics.getFramesPerSecond());
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Called when window is resized
        // Update the viewport to handle screen resizing
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        // Called when this screen is replaced
    }

    @Override
    public void dispose() {
        // Dispose of assets
        stage.dispose();
        fontTTF.dispose();
    }


}
