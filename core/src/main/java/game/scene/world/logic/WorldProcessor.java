package game.scene.world.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.config.Paths;
import game.scene.level.rendering.LevelScreen;
import game.scene.menu.rendering.MenuScreen;
import game.common.tilemap.BaseConfiguration;
import game.common.logic.entity.Entity;
import game.scene.level.logic.LevelConfiguration;
import game.common.logic.Direction;

public class WorldProcessor extends InputAdapter {
    private final Game game;
    private final World world;
    private final Entity player;
    private int previousKey;

    public WorldProcessor(Game game, World world) {
        this.game = game;
        this.world = world;
        this.player = world.getEntities().get(0);
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
            LevelConfiguration levelConfiguration = BaseConfiguration.listFrom(
                LevelConfiguration.class, Paths.LEVEL_CONFIG).get(0);
            game.setScreen(new LevelScreen(game, levelConfiguration));
        }
        else if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MenuScreen(game));
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
