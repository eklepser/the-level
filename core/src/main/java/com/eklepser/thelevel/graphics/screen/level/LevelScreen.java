package com.eklepser.thelevel.graphics.screen.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.screen.GameScreen;
import com.eklepser.thelevel.graphics.screen.level.editor.EditorProcessor;
import com.eklepser.thelevel.graphics.screen.level.window.HelpWindow;
import com.eklepser.thelevel.graphics.screen.level.window.WinWindow;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;

public class LevelScreen extends GameScreen {
    private final Level level;
    private final LevelLayout layout;
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;

    public LevelScreen(Game game, LevelConfiguration config) {
        super(config.tileMap);

        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);

        // Order is important! Level -> layout -> loadZones.
        level = new Level(config, this);
        layout = new LevelLayout(this, level);
        level.loadZones(config.tileMap, layout);
    }

    @Override
    protected void setupCamera() {
        adaptCamera();
    }

    @Override
    public void show() {
        stage.addActor(layout);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
        inputMultiplexer.addProcessor(new EditorProcessor(this));
        inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        level.update(delta);
        stage.act(delta);

        batch.begin();
        level.getEntities().forEach(entity -> entity.draw(batch, 1.0f));
        batch.end();

        stage.draw();
    }

    // Getters:
    public LevelLayout getLayout() {
        return layout;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public WinWindow getWinWindow() {
        return winWindow;
    }
}

