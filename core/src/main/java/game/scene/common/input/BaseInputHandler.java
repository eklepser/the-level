package game.scene.common.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class BaseInputHandler extends InputAdapter {
    private final BaseInputListener listener;

    public BaseInputHandler(BaseInputListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        listener.onZoom(amountY);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        listener.onScreenTapped();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.ESCAPE:
                listener.onEscapePressed();
                return true;
            case Input.Keys.ENTER:
                listener.onEnterPressed();
                return true;
            case Input.Keys.SPACE:
                listener.onSpace();
                return true;
            case Input.Keys.UP:
                listener.onArrowUp();
                return true;
            case Input.Keys.DOWN:
                listener.onArrowDown();
                return true;
            case Input.Keys.LEFT:
                listener.onArrowLeft();
                return true;
            case Input.Keys.RIGHT:
                listener.onArrowRight();
                return true;
            case Input.Keys.TAB:
                listener.onTab();
                return true;
            case Input.Keys.DEL:
                listener.onDelete();
                return true;
            case Input.Keys.F1:
                listener.onF1();
                return true;
            default:
                return false;
        }
    }
}
