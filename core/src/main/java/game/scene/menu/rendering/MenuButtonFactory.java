package game.scene.menu.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Json;
import game.config.Paths;
import game.data.world.WorldData;
import game.scene.common.rendering.ButtonFactory;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.rendering.WorldScreen;

public final class MenuButtonFactory extends ButtonFactory {
    public static TextButton startButton( ) {
        return createButton("Start Game", () -> {
            Json json = new Json();
            WorldData worldData = json.fromJson(WorldData.class, Gdx.files.local(Paths.WORLD_ONE));
            ScreenNavigator.gotoScreen(new WorldScreen(worldData));
        });
    }

    public static TextButton levelsButton() {
        return createButton("Levels", () ->
            ScreenNavigator.gotoScreen(new SelectionScreen(PlaySelectionLayout.class)));
    }

    public static TextButton builderButton() {
        return createButton("Build Level", () ->
            ScreenNavigator.gotoScreen(new SelectionScreen(BuilderSelectionLayout.class)));
    }

    public static TextButton exitButton() {
        return createButton("Exit", () -> Gdx.app.exit());
    }
}
