package com.deeep.spaceglad.components;

import com.badlogic.ashley.core.Component;

public class PlayerComponent extends Component {
    public float health;
    public static int score;

    public PlayerComponent() {
        health = 100;
        score = 0;
    }
}