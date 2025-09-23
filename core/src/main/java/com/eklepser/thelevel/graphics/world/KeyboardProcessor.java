package com.eklepser.thelevel.graphics.world;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.eklepser.thelevel.graphics.level.LevelScreen;
import com.eklepser.thelevel.graphics.menu.MenuScreen;
import com.eklepser.thelevel.logic.game.world.World;
import com.eklepser.thelevel.logic.interaction.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class KeyboardProcessor extends InputAdapter {
    private final Game game;
    private final World world;
    private final Entity player;
    private int previousKey;

    public KeyboardProcessor(Game game, World world) {
        this.game = game;
        this.world = world;
        this.player = world.getPlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            player.setMoving(Direction.UP);
            return true;
        }
        else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            player.setMoving(Direction.LEFT);
            return true;
        }
        else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            player.setMoving(Direction.DOWN);
            return true;
        }
        else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.setMoving(Direction.RIGHT);
            return true;
        }
        else if (keycode == Input.Keys.ENTER) {
            game.setScreen(new LevelScreen(game, world.getSelectedLevelInfo()));
        }
        else if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MenuScreen(game));
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
