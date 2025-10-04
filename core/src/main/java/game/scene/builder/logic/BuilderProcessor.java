package game.scene.builder.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.MathUtils;
import game.scene.builder.rendering.BuilderScreen;
import game.common.rendering.tile.TileMap;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.SelectionScreen;

public class BuilderProcessor extends InputAdapter {
    private final Game game;
    private final BuilderScreen screen;
    private final TileMap map;
    private int previousKey;

    public BuilderProcessor(Game game, BuilderScreen screen) {
        this.game = game;
        this.screen = screen;
        map = screen.getMap();
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        screen.getCamera().zoom(amountY);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            game.setScreen(new SelectionScreen(game, BuilderSelectionLayout.class));
        }
        if (keycode != previousKey) previousKey = keycode;
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screen.getStage().setKeyboardFocus(null);
        return false;
    }
}
