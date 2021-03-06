package com.emelwerx.world.services.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.emelwerx.world.WorldAdapter;
import com.emelwerx.world.services.Assets;
import com.emelwerx.world.services.Settings;

public class LeaderboardsScreen implements Screen {
    private WorldAdapter game;
    private Stage stage;
    private Image backgroundImage;
    private TextButton backButton;
    private Label label[];
    private boolean loaded;

    public LeaderboardsScreen(WorldAdapter game) {
        this.game = game;
        stage = new Stage(new FitViewport(WorldAdapter.VIRTUAL_WIDTH, WorldAdapter.VIRTUAL_HEIGHT));
        setWidgets();
        configureWidgers();
        setListeners();
        Gdx.input.setInputProcessor(stage);
    }

    private void setWidgets() {
        backgroundImage = new Image(new Texture(Gdx.files.internal("data/backgroundMN.png")));
        backButton = new TextButton("Back", Assets.skin);
        label = new Label[5];
        label[0] = new Label("Loading scores from online leaderborads...", Assets.skin);
        Settings.load(label);
    }

    private void configureWidgers() {
        backgroundImage.setSize(WorldAdapter.VIRTUAL_WIDTH, WorldAdapter.VIRTUAL_HEIGHT);
        backButton.setSize(128, 64);
        backButton.setPosition(WorldAdapter.VIRTUAL_WIDTH - backButton.getWidth() - 5, 5);

        stage.addActor(backgroundImage);
        stage.addActor(backButton);

        label[0].setFontScale(3);
        label[0].setPosition(15, WorldAdapter.VIRTUAL_HEIGHT - label[0].getHeight() - 25);
        stage.addActor(label[0]);
    }

    private void setListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        /** Updates */
        stage.act(delta);
        updateLeaderboard();
        /** Draw */
        stage.draw();
    }

    public void updateLeaderboard() {
        if (label[1] != null && loaded == false) {
            loaded = true;
            int y = 0;
            for (int i = 0; i < label.length; i++) {
                label[i].setFontScale(3);
                label[i].setPosition(15, WorldAdapter.VIRTUAL_HEIGHT - label[i].getHeight() - 25 - y);
                y += 96;
                stage.addActor(label[i]);
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}