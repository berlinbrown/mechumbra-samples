package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Elmar on 8-8-2015.
 */
public class PlayerComponent implements Component {

    public float health;
    public static int score;

    public PlayerComponent() {

        health = 100;
        score = 0;
    }
}