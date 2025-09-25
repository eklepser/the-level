package com.eklepser.thelevel.graphics.level;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.eklepser.thelevel.graphics.level.editor.KeyboardProcessor;
import com.eklepser.thelevel.graphics.level.root.RootTable;
import com.eklepser.thelevel.logic.world.GameMap;
import com.eklepser.thelevel.logic.world.GameScreen;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.util.Layout;

public class LevelScreen extends GameScreen {
    private final RootTable rootTable;
    private final HelpWindow helpWindow;
    private final WinWindow winWindow;
    private final InputMultiplexer multiplexer;

    public LevelScreen(Game game, Level level) {
        super(game, level);
        helpWindow = new HelpWindow(game);
        winWindow = new WinWindow(game);
        multiplexer = new InputMultiplexer();
        rootTable = new RootTable(this, level);
    }

    @Override
    public void show() {
        setupLayout();
        setupInputProcessors();
    }

    private void setupLayout() {
        stage.addActor(rootTable);
        stage.addActor(helpWindow);
        stage.addActor(winWindow);
    }

    private void setupInputProcessors() {
        multiplexer.addProcessor(new KeyboardProcessor(this));
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public RootTable getRootTable() {
        return rootTable;
    }

    public HelpWindow getHelpWindow() {
        return helpWindow;
    }

    public WinWindow getWinWindow() {
        return winWindow;
    }
}

