package com.deeep.spaceglad.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.deeep.spaceglad.Core;


public class DesktopLauncher {
    public static void main(String[] arg) {
        final Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Mech Umbra Samples");
        new Lwjgl3Application(new Core(), config);
    }
}
