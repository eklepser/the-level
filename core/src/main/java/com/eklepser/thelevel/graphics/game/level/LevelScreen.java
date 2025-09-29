package com.eklepser.thelevel.graphics.game.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.builder.GameScreen;
import com.eklepser.thelevel.graphics.builder.TileMap;
import com.eklepser.thelevel.graphics.game.level.editor.EditorProcessor;
import com.eklepser.thelevel.graphics.game.level.window.HelpWindow;
import com.eklepser.thelevel.graphics.game.level.window.WinWindow;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;

public class LevelScreen extends GameScreen {
    private final Level level;
    private final LevelLayout root;
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;

    public LevelScreen(Game game, LevelConfiguration config) {
        super(config);
        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);
        level = new Level(config, this);
        root = new LevelLayout(this, level);
        inputMultiplexer.addProcessor(new EditorProcessor(this));
    }

    @Override
    public void show() {
        stage.addActor(root);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
    }

    // Getters:
    public LevelLayout getRoot() {
        return root;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public WinWindow getWinWindow() {
        return winWindow;
    }
}

