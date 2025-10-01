package com.eklepser.thelevel.graphics.screen.selection;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.screen.Layout;
import com.eklepser.thelevel.graphics.screen.TableLayout;
import com.eklepser.thelevel.graphics.screen.builder.BuilderScreen;
import com.eklepser.thelevel.graphics.utils.TextLabel;
import com.eklepser.thelevel.logic.world.common.Configuration;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Resources;

import java.util.List;

public class SelectionLayout extends TableLayout {
    private final Game game;
    private final List<LevelMetadata> levels;

    public SelectionLayout(Game game, List<LevelMetadata> levels) {
        this.game = game;
        this.levels = levels;

        setup();
    }

    @Override
    public void setup() {
        setFillParent(true);

        add(new TextLabel("Create new level:")).row();

        TextButton createButton = new TextButton("Create", Resources.getSkin());
        createButton.addListener(new ButtonListener(game, "builder/builder_template.json"));
        add(createButton).padTop(10).width(Layout.VIEWPORT_WIDTH / 4.0f).row();

        add(new TextLabel("Edit level:")).padTop(20).row();

        for (LevelMetadata data : levels) {
            String text = String.format(data.tag());
            TextButton button = new TextButton(text, Resources.getSkin());
            String path = String.format("builder/level_%s.json" , data.tag());
            button.addListener(new ButtonListener(game, path));

            add(button).padTop(10).width(Layout.VIEWPORT_WIDTH / 4.0f).row();
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
            LevelConfiguration config = Configuration.from(
                LevelConfiguration.class, path);
            game.setScreen(new BuilderScreen(game, config));
        }
    }
}
