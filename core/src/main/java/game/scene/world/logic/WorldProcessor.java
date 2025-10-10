package game.scene.world.logic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.scene.common.logic.Direction;
import game.scene.common.logic.entity.Entity;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.menu.rendering.MenuScreen;

public class WorldProcessor extends InputAdapter {
    private final World world;
    private final Entity player;
    private int previousKey;

    public WorldProcessor(World world) {
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

        }
        else if (keycode == Input.Keys.ESCAPE) {
            ScreenNavigator.gotoScreen(new MenuScreen());
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
