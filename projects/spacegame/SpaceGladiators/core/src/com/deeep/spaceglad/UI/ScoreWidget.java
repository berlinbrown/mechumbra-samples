package com.deeep.spaceglad.UI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.deeep.spaceglad.components.PlayerComponent;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by scanevaro on 04/08/2015.
 */
public class ScoreWidget extends Actor {

    Label label;
    protected BitmapFont font;

    public ScoreWidget() {
        font = new BitmapFont();
        label = new Label("", new Label.LabelStyle(font, Color.WHITE));
    }

    @Override
    public void act(float delta) {
        label.act(delta);
        label.setText("Score : " + PlayerComponent.score);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        label.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        label.setSize(width, height);
    }
}