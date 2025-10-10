package game.scene.selection.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import game.common.rendering.TableLayout;
import game.common.rendering.component.TextLabel;
import game.common.rendering.screen.ScreenNavigator;
import game.common.rendering.tilemap.BaseConfiguration;
import game.config.Display;
import game.resources.Assets;
import game.scene.builder.rendering.BuilderScreen;
import game.scene.level.logic.LevelConfigurationOld;
import game.scene.level.logic.LevelData;
import game.scene.level.logic.LevelMetadata;
import game.scene.level.logic.LevelMetadataOld;
import game.scene.level.rendering.LevelScreen;

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
            String text = String.format(data.tag);
            TextButton button = new TextButton(text, Assets.getSkin());
            String path = String.format("data/builder/level_%s.json" , data.tag);
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
            Json json = new Json();
            LevelData levelData = json.fromJson(LevelData.class, Gdx.files.local(path));
            ScreenNavigator.gotoScreen(new BuilderScreen(levelData));
        }
    }
}
