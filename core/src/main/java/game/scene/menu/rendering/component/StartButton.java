package game.scene.menu.rendering.component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.level.rendering.LevelScreen;
import game.common.logic.Configuration;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;

public class StartButton extends TextButton {
    public StartButton(Game game) {
        super("START GAME", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LevelConfiguration config = Configuration.from(
                    LevelConfiguration.class, "builder/level_test.json");
                game.setScreen(new LevelScreen(game, config));
            }
        });
    }
}
