package game.scene.menu.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.common.rendering.screen.ScreenNavigator;
import game.common.rendering.tilemap.BaseConfiguration;
import game.common.rendering.ButtonFactory;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.rendering.WorldScreen;

public final class MenuButtonFactory extends ButtonFactory {
    public static TextButton startButton( ) {
        return createButton("Start Game", () -> {
            WorldConfiguration config = BaseConfiguration.from(
                WorldConfiguration.class, "world/world_one.json");
            ScreenNavigator.gotoScreen(new WorldScreen(config));
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
