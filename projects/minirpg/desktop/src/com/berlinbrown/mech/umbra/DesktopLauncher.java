package com.berlinbrown.mech.umbra;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.berlinbrown.mech.umbra.MechUmbraGdxRPGGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		final Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1000, 720);
		config.setResizable(false); // or false to lock size

		config.setTitle("Mech Umbra Samples");
		new Lwjgl3Application(new MechUmbraGdxRPGGame(), config);
	}
}
