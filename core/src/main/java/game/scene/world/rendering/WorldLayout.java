package game.scene.world.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import game.config.Display;
import game.data.IO.Assets;
import game.data.level.LevelData;
import game.data.user.LevelStatus;
import game.data.world.WorldData;
import game.scene.common.logic.event.EventListener;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.scene.world.logic.World;
import game.scene.world.logic.event.WorldEvent;
import game.scene.world.logic.event.WorldTurnEvent;
import game.scene.world.rendering.component.LevelStatusLayout;

public final class WorldLayout extends TableLayout implements EventListener<WorldEvent> {
    private final World world;
    private final LevelStatusLayout levelStatusLayout;
    private final TextButton playButton;

    public WorldLayout(World world) {
        this.world = world;

        levelStatusLayout = new LevelStatusLayout();
        playButton = new TextButton("Play", Assets.getSkin());
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                world.startLevel();
            }
        });

        world.subscribe(this);

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(levelStatusLayout).left().padLeft(20).expandX();
        row();
        add().expandY();
        row();
        add(playButton).width( Display.VIEWPORT_WIDTH / 5f).padBottom(32).height(Display.VIEWPORT_HEIGHT / 12.0f);
    }

    @Override
    public void onSubscribe() {
        updateStatus();
    }

    @Override
    public void onEvent(WorldEvent event) {
        if (event instanceof WorldTurnEvent) {
            updateStatus();
        }
    }

    private void updateStatus() {
        LevelData levelData = world.getSelectedLevelData();
        LevelStatus levelStatus = world.getSelectedLevelStatus();
        if (levelData == null || levelStatus == null) return;

        levelStatusLayout.setStatus(levelData, levelStatus);
    }
}
