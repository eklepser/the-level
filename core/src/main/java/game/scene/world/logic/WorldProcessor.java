package game.scene.world.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.scene.level.rendering.LevelScreen;
import game.scene.menu.rendering.MenuScreen;
import game.scene.common.logic.Configuration;
import game.scene.common.logic.entity.Entity;
import game.scene.level.logic.LevelConfiguration;
import game.scene.common.logic.Direction;
import game.resources.Assets;

public class WorldProcessor extends InputAdapter {
    private final Game game;
    private final World world;
    private final Entity player;
    private int previousKey;

    public WorldProcessor(Game game, World world) {
        this.game = game;
        this.world = world;
        this.player = world.getPlayer();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            player.setTargetWorldPos(Direction.UP);
            return true;
        }
        else if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            player.setTargetWorldPos(Direction.LEFT);
            return true;
        }
        else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            player.setTargetWorldPos(Direction.DOWN);
            return true;
        }
        else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.setTargetWorldPos(Direction.RIGHT);
            return true;
        }
        else if (keycode == Input.Keys.ENTER || keycode == Input.Keys.SPACE) {
            if (world.getSelectedLevelId() != 0) {
                //game.setScreen(new LevelScreen(game, world.getSelectedLevelConfig()));
            }
        }
        else if (keycode == Input.Keys.F1) {
            world.setSelectedLevelId(0);
            LevelConfiguration levelConfiguration = Configuration.listFrom(
                LevelConfiguration.class, Assets.LEVEL_CONFIG).get(0);
            System.out.println("AAAAAAAAA");
            game.setScreen(new LevelScreen(game, levelConfiguration));
        }
        else if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MenuScreen(game));
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
