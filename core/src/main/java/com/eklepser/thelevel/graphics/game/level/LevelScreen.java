package com.eklepser.thelevel.graphics.game.level;

import com.badlogic.gdx.Game;
import com.eklepser.thelevel.graphics.game.level.editor.EditorProcessor;
import com.eklepser.thelevel.graphics.game.GameScreen;
import com.eklepser.thelevel.graphics.game.level.window.HelpWindow;
import com.eklepser.thelevel.graphics.game.level.window.WinWindow;
import com.eklepser.thelevel.logic.world.level.Level;

public class LevelScreen extends GameScreen {
    private final LevelLayout root;
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;

    public LevelScreen(Game game, Level level) {
        super(level);
        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);
        root = new LevelLayout(this, level);
    }

    @Override
    public void show() {
        stage.addActor(root);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
        inputMultiplexer.addProcessor(new EditorProcessor(this));
        inputMultiplexer.addProcessor(stage);
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

