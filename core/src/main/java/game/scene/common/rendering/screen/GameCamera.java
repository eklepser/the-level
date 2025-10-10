package game.scene.common.rendering.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import game.config.Display;

public class GameCamera extends OrthographicCamera {
    public GameCamera() {
        setToOrtho(false, Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT);
    }

    public GameCamera(float viewportWidth, float viewportHeight) {
        setToOrtho(false, viewportWidth, viewportHeight);
    }

    public void center(float width, float height) {
        float centerX = width / 2f;
        float centerY = height / 2f;
        position.set(centerX, centerY, 0);

        update();
    }

    public void offset(float offsetX, float offsetY) {
        position.set(position.x + offsetX, position.y + offsetY, 0);

        update();
    }

    public void zoom(float zoomValue) {
        float zoomChange = zoomValue * 0.05f;
        zoom = MathUtils.clamp(zoom + zoomChange, 0.1f, 2.0f);

        update();
    }
}
