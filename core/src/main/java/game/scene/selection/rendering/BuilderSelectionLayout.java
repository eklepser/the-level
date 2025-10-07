package game.scene.selection.rendering;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.common.ScreenNavigator;
import game.config.Display;
import game.common.rendering.TableLayout;
import game.scene.builder.rendering.BuilderScreen;
import game.common.rendering.component.TextLabel;
import game.common.tilemap.BaseConfiguration;
import game.scene.level.logic.LevelConfiguration;
import game.resources.Assets;
import game.scene.selection.logic.LevelMetadata;

import java.util.List;

public final class BuilderSelectionLayout extends TableLayout {
    private final List<LevelMetadata> levels;

    public BuilderSelectionLayout(List<LevelMetadata> levels) {
        this.levels = levels;

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Create new level:")).row();

        TextButton createButton = new TextButton("Create", Assets.getSkin());
        createButton.addListener(new ButtonListener("data/builder/builder_template.json"));
        add(createButton).padTop(10).width(Display.VIEWPORT_WIDTH / 4.0f).row();

        add(new TextLabel("Edit level:")).padTop(20).row();

        for (LevelMetadata data : levels) {
            String text = String.format(data.tag());
            TextButton button = new TextButton(text, Assets.getSkin());
            String path = String.format("data/builder/level_%s.json" , data.tag());
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
            LevelConfiguration config = BaseConfiguration.from(
                LevelConfiguration.class, path);
            ScreenNavigator.gotoScreen(new BuilderScreen(config));
        }
    }
}
