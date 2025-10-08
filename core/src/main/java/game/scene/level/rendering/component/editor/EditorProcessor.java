package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.common.logic.Direction;

public final class EditorProcessor extends InputAdapter {
    private final EditorLayout layout;
    private int previousKey;

    public EditorProcessor(EditorLayout layout) {
        this.layout = layout;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (previousKey == Input.Keys.CONTROL_LEFT) {
            // Run code
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
