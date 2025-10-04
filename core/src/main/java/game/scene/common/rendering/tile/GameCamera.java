package game.scene.common.rendering.tile;

import com.badlogic.gdx.graphics.OrthographicCamera;
import game.config.GraphicsConstants;

public class GameCamera extends OrthographicCamera {

    public GameCamera(int viewportWidth, int viewportHeight) {
        setToOrtho(false, viewportWidth, viewportHeight);
    }

    public void center(int width, int height) {
        float centerX = width / 2f;
        float centerY = height / 2f;
        position.set(centerX, centerY, 0);
        update();
    }

    public void adapt(int width, int height) {
        float zoom = 16.0f / width;
        float cameraX = (width * GraphicsConstants.TILE_SIZE - GraphicsConstants.EDITOR_MENU_SCALE * GraphicsConstants.VIEWPORT_WIDTH / zoom) / 2.0f;
        float cameraY = height * GraphicsConstants.TILE_SIZE / 2.0f;

        setToOrtho(false, GraphicsConstants.VIEWPORT_WIDTH / zoom,
            GraphicsConstants.VIEWPORT_HEIGHT / zoom);
        position.set(cameraX, cameraY, 0);
        update();
    }
}
