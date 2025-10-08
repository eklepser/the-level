package game.scene.builder.rendering;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import game.common.input.BaseInputHandler;
import game.common.input.BaseInputListener;
import game.common.rendering.screen.DynamicGameCamera;
import game.common.rendering.screen.GameScreen;
import game.common.rendering.screen.ScreenNavigator;
import game.config.Display;
import game.scene.builder.logic.Builder;
import game.scene.level.logic.LevelConfiguration;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.SelectionScreen;

public final class BuilderScreen extends GameScreen implements BaseInputListener {
    private final DynamicGameCamera camera;
    private final Builder builder;
    private final Stage gridStage;
    private final BuilderLayout layout;

    public BuilderScreen(LevelConfiguration config) {
        super(config.tileMap);

        camera = new DynamicGameCamera();
        builder = new Builder(config);

        // Order is important! Builder -> gridStage -> layout.
        gridStage = new Stage(new FitViewport(
            Display.VIEWPORT_WIDTH, Display.VIEWPORT_HEIGHT, camera));
        gridStage.addActor(builder.getGridActor());

        layout = new BuilderLayout(this, builder);
        stage.addActor(layout);
    }

    @Override
    public void show() {
        camera.center(map.width * Display.TILE_SIZE,
            map.height * Display.TILE_SIZE);

        multiplexer.addProcessor(new BaseInputHandler(this));

        multiplexer.addProcessor(gridStage);
    }

    @Override
    protected void update(float delta) {
        camera.moveAndUpdate(delta);
        layout.update();
        gridStage.act();
    }

    @Override
    protected void draw() {
        gridStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        gridStage.getViewport().update(width, height, true);
        camera.center(map.width * Display.TILE_SIZE,
            map.height * Display.TILE_SIZE);
    }

    // Listener methods:
    @Override
    public void onEscapePressed() {
        ScreenNavigator.gotoScreen(new SelectionScreen(BuilderSelectionLayout.class));
    }

    @Override
    public void onEnterPressed() {
        stage.setKeyboardFocus(null);
    }

    @Override
    public void onScreenTapped() {
        stage.setKeyboardFocus(null);
    }

    @Override
    public void onZoom(float amountY) {
        camera.zoom(amountY);
    }

    // Getters:
    public Builder getBuilder() {
        return builder;
    }

    public Stage getGridStage() {
        return gridStage;
    }

    public DynamicGameCamera getCamera() {
        return camera;
    }
}
