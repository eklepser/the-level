package game.scene.level.rendering.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.common.rendering.screen.ScreenNavigator;
import game.scene.world.rendering.WorldScreen;
import game.common.rendering.tilemap.BaseConfiguration;
import game.scene.world.logic.WorldConfiguration;
import game.resources.Assets;

public final class ExitLevelButton extends TextButton {
    public ExitLevelButton() {
        super("Exit", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit");
                WorldConfiguration worldConfig = BaseConfiguration.listFrom(
                    WorldConfiguration.class, "world/world.json").get(0);
                ScreenNavigator.gotoScreen(new WorldScreen(worldConfig));
            }
        });
    }
}
