package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.common.tilemap.BaseConfiguration;
import game.common.rendering.ButtonFactory;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.logic.WorldConfiguration;
import game.scene.world.rendering.WorldScreen;

public final class MenuButtonFactory extends ButtonFactory {
    public static TextButton startButton(Game game) {
        return createButton("Start Game", () -> {
            WorldConfiguration config = BaseConfiguration.from(
                WorldConfiguration.class, "world/world_one.json");
            game.setScreen(new WorldScreen(game, config));
        });
    }

    public static TextButton levelsButton(Game game) {
        return createButton("Levels", () ->
            game.setScreen(new SelectionScreen(game, PlaySelectionLayout.class)));
    }

    public static TextButton builderButton(Game game) {
        return createButton("Build Level", () ->
            game.setScreen(new SelectionScreen(game, BuilderSelectionLayout.class)));
    }

    public static TextButton exitButton() {
        return createButton("Exit", () -> Gdx.app.exit());
    }
}
