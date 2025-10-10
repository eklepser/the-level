package game.scene.level.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.resources.Assets;
import game.scene.world.data.WorldData;
import game.scene.world.rendering.WorldScreen;

public final class ExitLevelButton extends TextButton {
    public ExitLevelButton() {
        super("Exit", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Json json = new Json();
                WorldData worldData = json.fromJson(WorldData.class, Gdx.files.internal("world/world.json"));
                ScreenNavigator.gotoScreen(new WorldScreen(worldData));
            }
        });
    }
}
