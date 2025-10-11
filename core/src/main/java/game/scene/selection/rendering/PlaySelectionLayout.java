package game.scene.selection.rendering;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.config.Display;
import game.config.Paths;
import game.data.level.LevelData;
import game.data.level.LevelLoader;
import game.data.level.LevelMetadata;
import game.data.resources.Assets;
import game.scene.common.rendering.TableLayout;
import game.scene.common.rendering.component.TextLabel;
import game.scene.common.rendering.screen.ScreenNavigator;
import game.scene.level.rendering.LevelScreen;

import java.util.List;

public final class PlaySelectionLayout extends TableLayout {
    private final List<LevelMetadata> levels;

    public PlaySelectionLayout(List<LevelMetadata> levels) {
        this.levels = levels;

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Play level:")).padTop(20).row();

        for (LevelMetadata data : levels) {
            String text = String.format(data.tag);
            TextButton button = new TextButton(text, Assets.getSkin());
            String path = String.format("%slevel_%s.json" , Paths.BUILDER_DATA, data.tag);
            button.addListener(new ButtonListener(path));

            add(button).padTop(10).width(Display.VIEWPORT_WIDTH / 4.0f).row();
        }
    }

    // Button listener class:
    private static class ButtonListener extends ClickListener {
        private final String path;

        public ButtonListener(String path) {
            this.path = path;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            LevelData levelData = LevelLoader.loadData(path);
            ScreenNavigator.gotoScreen(new LevelScreen(levelData));
        }
    }
}
