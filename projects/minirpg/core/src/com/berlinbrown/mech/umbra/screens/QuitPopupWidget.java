package com.berlinbrown.mech.umbra.screens;

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
        Table table = new Table(skin);
        table.setBackground(skin.newDrawable("white", new Color(1, 1, 1, 0.95f)));
        table.pad(20);
        table.defaults().pad(10);

        Label title = new Label("Quit Game?", skin);
        table.add(title).colspan(2).center().row();

        TextButton quit1 = new TextButton("Quit", skin);
        quit1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit");
            }
        });

        TextButton quit2 = new TextButton("Quit", skin);
        quit2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit");
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
        Skin skin = new Skin();
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.BLACK);
        skin.add("default", labelStyle);

        TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
        tbs.up = skin.newDrawable("white", Color.LIGHT_GRAY);
        tbs.down = skin.newDrawable("white", Color.DARK_GRAY);
        tbs.font = font;
        skin.add("default", tbs);

        return skin;
    }
}
