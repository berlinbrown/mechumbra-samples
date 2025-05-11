/**
 * Berlin Brown - https://www.myberlinaustin.com/ - 2025
 */
package com.berlinbrown.mech.umbra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class PopupExitScreen implements Screen {

    private final Stage stage;
    private final  Table root;
    private final  Skin skin;

    public PopupExitScreen() {
        stage = new Stage(new ScreenViewport());
        skin = createBasicSkin();

        root = new Table(skin);
        root.setBackground(skin.newDrawable("white", new Color(1, 1, 1, 0.95f)));
        root.setSize(300, 200);
        root.setPosition(
                (Gdx.graphics.getWidth() - root.getWidth()) / 2,
                (Gdx.graphics.getHeight() - root.getHeight()) / 2
        );
        root.pad(15);
        root.defaults().pad(10);

        final Label title = new Label("Exit Game?", skin);
        title.setAlignment(Align.center);
        root.add(title).colspan(2).center().row();

        TextButton quit1 = new TextButton("Quit!", skin);
        quit1.addListener(new ClickListener() {
            public void clicked(final InputEvent event, final float x, final float y) {
                System.out.println("Quit Clicked");
            }
        });

        final TextButton quit2 = new TextButton("Quit!", skin);
        quit2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit Clicked");
            }
        });

        root.add(quit1).uniform().fill();
        root.add(quit2).uniform().fill();
        stage.addActor(root);
    }

    private Skin createBasicSkin() {
        final Skin skin = new Skin();
        final BitmapFont font = new BitmapFont();
        skin.add("default", font);

        final Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        final Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.BLACK;
        skin.add("default", labelStyle);

        final TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.newDrawable("white", Color.LIGHT_GRAY);
        buttonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.BLACK;
        skin.add("default", buttonStyle);

        return skin;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
