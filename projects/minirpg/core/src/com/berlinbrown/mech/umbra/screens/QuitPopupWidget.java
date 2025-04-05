package com.berlinbrown.mech.umbra.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class QuitPopupWidget extends Group {

    public QuitPopupWidget(final Skin skin) {
        final Table table = new Table(skin);
        table.setBackground(skin.newDrawable("white", new Color(1, 1, 1, 0.95f)));
        table.pad(20);
        table.defaults().pad(10);

        // First row as title
        final Label title = new Label("Quit Game?", skin);
        table.add(title).colspan(2).center().row();

        final TextButton quit1 = new TextButton("Continue", skin);
        quit1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Continue");

                // Remove popup
                remove();
            }
        });

        final TextButton quit2 = new TextButton("Quit", skin);
        quit2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit");
                Gdx.app.exit();
            }
        });

        table.add(quit1).uniform().fill();
        table.add(quit2).uniform().fill();

        // Set widget size
        table.pack();
        setSize(table.getWidth(), table.getHeight());

        // Center it (you can also control this from the outside)
        table.setPosition(0, 0);
        addActor(table);
    }

    public static Skin createBasicSkin() {
        final Skin skin = new Skin();
        final BitmapFont font = createFont("fonts/Roboto-Regular.ttf", 14);
        skin.add("default", font);

        final Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        final Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
        skin.add("default", labelStyle);

        final TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.up = skin.newDrawable("white", Color.LIGHT_GRAY);
        tbs.down = skin.newDrawable("white", Color.DARK_GRAY);
        tbs.font = font;
        skin.add("default", tbs);

        return skin;
    }

    private static BitmapFont createFont(final String fontPath, final int size) {
        final FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        final FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;  // Set font size
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        final BitmapFont font = generator.generateFont(parameter);
        generator.dispose();  // Dispose generator after use
        return font;
    }
}
