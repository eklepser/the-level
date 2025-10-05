package game.scene.menu.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.common.logic.MapConfiguration;
import game.common.rendering.ButtonFactory;
import game.scene.builder.rendering.BuilderLayout;
import game.scene.level.logic.LevelConfiguration;
import game.scene.level.rendering.LevelScreen;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;

public final class MenuButtonFactory extends ButtonFactory {
    public static TextButton createStartButton(Game game) {
        return createButton("Start Game", () -> {
            LevelConfiguration config = MapConfiguration.from(
                LevelConfiguration.class, "builder/level_test.json");
            game.setScreen(new LevelScreen(game, config));
        });
    }

    public static TextButton createLevelsButton(Game game) {
        return createButton("Levels", () -> {
            game.setScreen(new SelectionScreen(game, PlaySelectionLayout.class));
        });
    }

    public static TextButton createBuilderButton(Game game) {
        return createButton("Build Level", () -> {
            game.setScreen(new SelectionScreen(game, BuilderLayout.class));
        });
    }

    public static TextButton createExitButton() {
        return createButton("Exit", () -> {
            Gdx.app.exit();
        });
    }
}
