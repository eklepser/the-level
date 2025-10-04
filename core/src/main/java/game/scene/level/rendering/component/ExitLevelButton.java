package game.scene.level.rendering.component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.world.rendering.WorldScreen;
import game.scene.common.logic.Configuration;
import game.scene.world.logic.WorldConfiguration;
import game.resources.Assets;

public class ExitLevelButton extends TextButton {
    public ExitLevelButton(Game game) {
        super("Exit", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                WorldConfiguration worldConfig = Configuration.listFrom(
                    WorldConfiguration.class, "world/world.json").get(0);
                game.setScreen(new WorldScreen(game, worldConfig));
            }
        });
    }
}
