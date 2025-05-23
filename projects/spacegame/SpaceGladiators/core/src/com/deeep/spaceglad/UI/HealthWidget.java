package com.deeep.spaceglad.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Align;
import com.deeep.spaceglad.Settings;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by scanevaro on 01/08/2015.
 */
public class HealthWidget extends Actor {

    private ProgressBar healthBar;
    private ProgressBar.ProgressBarStyle progressBarStyle;
    private Label label;
    private Skin skin;
    protected BitmapFont font;

    public HealthWidget() {

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        progressBarStyle = new ProgressBar.ProgressBarStyle(
                skin.newDrawable("white", Color.RED),
                skin.newDrawable("white", Color.GREEN));

        font = new BitmapFont();

        progressBarStyle.knobBefore = progressBarStyle.knob;
        healthBar = new ProgressBar(0, 100, 1, false, progressBarStyle);
        label = new Label("Health", new Label.LabelStyle(font, Color.WHITE));
        label.setAlignment(Align.center);
    }

    @Override
    public void act(float delta) {
        if (Settings.Paused) return;
        healthBar.act(delta);
        label.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        healthBar.draw(batch, parentAlpha);
        label.draw(batch, parentAlpha);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        healthBar.setPosition(x, y);
        label.setPosition(x, y);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        healthBar.setSize(width, height);
        progressBarStyle.background.setMinWidth(width);
        progressBarStyle.background.setMinHeight(height);
        progressBarStyle.knob.setMinWidth(healthBar.getValue());
        progressBarStyle.knob.setMinHeight(height);
        label.setSize(width, height);
    }

    public void setValue(float value) {
        healthBar.setValue(value);
    }
}