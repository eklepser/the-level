package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Json;
import game.data.world.WorldData;
import game.scene.common.logic.Direction;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.rendering.WorldScreen;

public final class LevelProcessor extends InputAdapter {
    private final EditorLayout layout;
    private int previousKey;

    public LevelProcessor(EditorLayout layout) {
        this.layout = layout;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (previousKey == Input.Keys.CONTROL_LEFT) {
            if (keycode == Input.Keys.ENTER) {
                layout.runCode();
                previousKey = 0;
                return true;
            }
            if (keycode == Input.Keys.BACKSLASH) {
                layout.resetCode();
                previousKey = 0;
                return true;
            }
        }
        else if (keycode == Input.Keys.ESCAPE) {
            Json json = new Json();
            if (ScreenNavigator.getPreviousScreen() instanceof WorldScreen) {
                WorldData worldData = json.fromJson(WorldData.class, Gdx.files.internal("world/world_one.json"));
                ScreenNavigator.gotoScreen(new WorldScreen(worldData));
            }
            else  {
                ScreenNavigator.gotoScreen(new SelectionScreen(PlaySelectionLayout.class));
            }
            return true;
        }
        else if (keycode == Input.Keys.F1) {
            layout.getParametersTable().setNextSliderValue();
            return true;
        }
        else if (keycode == Input.Keys.F2) {
            layout.getParametersTable().setPrevSliderValue();
            return true;
        }
        else if (keycode == Input.Keys.F4) {
            layout.resetCode();
            return true;
        }
        else if (keycode == Input.Keys.F5) {
            layout.runCode();
            return true;
        }
        else if (keycode == Input.Keys.DOWN || keycode == Input.Keys.ENTER) {
            layout.getCodeLayout().setFocusOnLine(Direction.DOWN);
            return true;
        }
        else if (keycode == Input.Keys.UP || keycode == Input.Keys.TAB) {
            layout.getCodeLayout().setFocusOnLine(Direction.UP);
            return true;
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
