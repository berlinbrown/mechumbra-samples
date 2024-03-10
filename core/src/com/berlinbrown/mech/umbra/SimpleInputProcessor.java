package com.berlinbrown.mech.umbra;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class SimpleInputProcessor implements InputProcessor {

    static float x = 300.0f;

    static float y = 300.0f;

    public boolean keyDown (int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                System.out.println("Running Left " + x);
                x -= 0.8f;
                break;
            case Input.Keys.RIGHT:
                System.out.println("Running Right " + x);
                x += 0.8f;
                break;
        }
        return true;

    }

    public boolean keyUp (int keycode) {
        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (float amountX, float amountY) {
        return false;
    }
}