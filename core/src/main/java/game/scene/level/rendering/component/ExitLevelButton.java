package game.scene.level.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import game.data.IO.Assets;
import game.data.world.WorldData;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.level.rendering.LevelScreen;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.rendering.WorldScreen;

public final class ExitLevelButton extends TextButton {
    public ExitLevelButton() {
        super("Exit", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ScreenNavigator.getPreviousScreen() instanceof WorldScreen) {
                    Json json = new Json();
                    WorldData worldData = json.fromJson(WorldData.class, Gdx.files.internal("data/world/world_one.json"));
                    ScreenNavigator.gotoScreen(new WorldScreen(worldData));
                }
                else ScreenNavigator.gotoScreen(new SelectionScreen(BuilderSelectionLayout.class));
            }
        });
    }
}
