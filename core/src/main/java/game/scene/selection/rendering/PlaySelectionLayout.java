package game.scene.selection.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.config.Display;
import game.config.Paths;
import game.common.rendering.TableLayout;
import game.scene.level.rendering.LevelScreen;
import game.common.rendering.component.TextLabel;
import game.common.tilemap.BaseConfiguration;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;
import game.scene.selection.logic.LevelMetadata;

import java.util.List;

public final class PlaySelectionLayout extends TableLayout {
    private final Game game;
    private final List<LevelMetadata> levels;

    public PlaySelectionLayout(Game game, List<LevelMetadata> levels) {
        this.game = game;
        this.levels = levels;

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Play level:")).padTop(20).row();

        for (LevelMetadata data : levels) {
            String text = String.format(data.tag());
            TextButton button = new TextButton(text, Assets.getSkin());
            String path = String.format("%slevel_%s.json" , Paths.BUILDER_DATA, data.tag());
            button.addListener(new ButtonListener(game, path));

            add(button).padTop(10).width(Display.VIEWPORT_WIDTH / 4.0f).row();
        }
    }

    // Button listener class:
    private static class ButtonListener extends ClickListener {
        private final Game game;
        private final String path;

        public ButtonListener(Game game, String path) {
            this.game = game;
            this.path = path;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            LevelConfiguration config = BaseConfiguration.from(
                LevelConfiguration.class, path);
            game.setScreen(new LevelScreen(game, config));
        }
    }
}
