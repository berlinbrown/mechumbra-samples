package com.berlinbrown.mech.umbra;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.berlinbrown.mech.umbra.MechUmbraGdxGame;

/**
 * The Launcher is the main desktop launcher
 * Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
 */
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Mech Umbra Samples");
		new Lwjgl3Application(new MechUmbraGdxGame(), config);
	}
}
