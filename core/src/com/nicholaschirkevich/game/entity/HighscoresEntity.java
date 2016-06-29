package com.nicholaschirkevich.game.entity;

/**
 * Created by Nikolas on 25.06.2016.
 */
public class HighscoresEntity {
    private String id,highscores;

    public HighscoresEntity(String id, String highscores) {
        this.id = id;
        this.highscores = highscores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHighscores() {
        return highscores;
    }

    public void setHighscores(String highscores) {
        this.highscores = highscores;
    }
}
