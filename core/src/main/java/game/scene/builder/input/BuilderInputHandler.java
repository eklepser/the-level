package game.scene.builder.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class BuilderInputHandler extends InputAdapter {
    private final BuilderInputListener listener;

    public BuilderInputHandler(BuilderInputListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        listener.onZoom(amountY);
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            listener.onEscapePressed();
            return true;
        }
        if (keycode == Input.Keys.ENTER) {
            listener.onEnterPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        listener.onScreenTapped();
        return false;
    }


}
