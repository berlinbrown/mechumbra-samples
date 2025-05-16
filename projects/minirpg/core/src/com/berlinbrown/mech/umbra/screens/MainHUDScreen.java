/**
 * Berlin Brown - https://www.myberlinaustin.com/ - 2025
 */
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
import com.berlinbrown.mech.umbra.MechUmbraGdxRPGGame;

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

    /**
     * Render text only for the HUD.
     *
     * @param delta
     */
    @Override
    public void render(float delta) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

        // Position the label in the upper-left corner
        labelTTF.setPosition(10, Gdx.graphics.getHeight() - 40);

        final StringBuilder buf = new StringBuilder();
        buf.append("[Umbra Home] FPS: " + Gdx.graphics.getFramesPerSecond() + MechUmbraGdxRPGGame.timeElapsed + " -- "
                + MechUmbraGdxRPGGame.counter + " -- " + MechUmbraGdxRPGGame.lastMessage);
        buf.append("\n");
        //buf.append("1 ----- Player Status : \n");

        //buf.append("2 { Hero Strength : " + MechUmbraGdxRPGGame.hero.strength).append("\n");
        //buf.append("3 { Hero Constitution : " + MechUmbraGdxRPGGame.hero.constitution).append("\n");
        //buf.append("4 { Hero Dexterity : " + MechUmbraGdxRPGGame.hero.dexterity).append("\n");
        //buf.append("5 { Hero Defense : " +  MechUmbraGdxRPGGame.hero.defense).append("\n");
        ///buf.append("6 { Hero Attack : " + MechUmbraGdxRPGGame.hero.attackDamage).append("\n");
        buf.append("7 { Hero Health Points-HP : " + MechUmbraGdxRPGGame.hero.healthPoints);

        buf.append("---- 9 { Enemy Strength : " + MechUmbraGdxRPGGame.enemy.strength).append("\n");
        buf.append("10 { Enemy Constitution : " + MechUmbraGdxRPGGame.enemy.constitution).append("\n");
        //buf.append("11 { Enemy Dexterity : " + MechUmbraGdxRPGGame.enemy.dexterity).append("\n");
        //buf.append("12 { Enemy Defense : " +  MechUmbraGdxRPGGame.enemy.defense).append("\n");
        buf.append("14 { Enemy Health Points-HP : " + MechUmbraGdxRPGGame.enemy.healthPoints);

        labelTTF.setText(buf.toString());
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
