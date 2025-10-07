package game.scene.selection.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.common.ScreenNavigator;
import game.scene.menu.rendering.MenuScreen;

public class SelectionProcessor extends InputAdapter {
    private int previousKey;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            ScreenNavigator.gotoScreen(new MenuScreen());
        }

        if (keycode != previousKey) previousKey = keycode;
        return false;
    }
}
