package game.common.rendering.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class DynamicGameCamera extends GameCamera {
    public DynamicGameCamera() {
        super();
    }

    public DynamicGameCamera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    public void moveAndUpdate(float delta) {

        float moveDistance = 200 * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += moveDistance;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= moveDistance;
        }
        super.update();
    }
}
