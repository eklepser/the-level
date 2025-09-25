package com.eklepser.thelevel.logic.decoder.execution;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.level.editor.CodeLine;
import com.eklepser.thelevel.graphics.level.editor.Editor;
import com.eklepser.thelevel.graphics.level.root.StatusBar;
import com.eklepser.thelevel.logic.decoder.command.Command;
import com.eklepser.thelevel.logic.decoder.util.TimeController;
import com.eklepser.thelevel.logic.decoder.util.TimedAction;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.logic.world.level.Level;
import com.eklepser.thelevel.logic.world.level.LevelConfiguration;
import com.eklepser.thelevel.logic.world.zone.Collidable;

import java.util.List;
import java.util.Map;

public class Executor implements TimeController {
    private final Translator translator;
    private final LevelConfiguration conf;
    private final List<CodeLine> codeLines;
    private final Editor editor;
    private Map<CodeLine, Command> codeMap;
    private final List<Entity> targets;
    private final List<Collidable> zones;
    private float executionDelay = 0.5f;
    private int currentLineNum;
    private final StatusBar statusbar;

    public Executor(Level level, LevelConfiguration conf, Editor editor) {
        targets = level.getEntities();
        zones = level.getCollidables();
        this.conf = conf;
        this.editor = editor;
        statusbar = editor.getRootTable().getStatusBar();
        codeLines = editor.getCodeTable().getCodeLines();
        translator = new Translator(conf.allowedInstructions, codeLines, this);
    }

    public String runExecution() {
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            codeMap = translator.getCodeMap();
            execute(0, codeMap);
        }
        return result.message();
    }

    public void execute(int start, Map<CodeLine, Command> codeMap) {
        for (Entity target : targets) {
            target.clearActions();
            SequenceAction action = createSequenceAction(start, codeMap, target);
            target.addAction(action);
        }
    }

    public SequenceAction createSequenceAction(int start, Map<CodeLine, Command> codeMap, Entity target) {
        System.out.println("Running");
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            CodeLine codeLine = codeLines.get(i);
            Command currentCmd = codeMap.get(codeLine);
            if (currentCmd == null) continue;
            int finalI = i;
            sequence.addAction(Actions.run(() -> setCompleting(finalI, codeLine)));
            sequence.addAction(new TimedAction(this));
            sequence.addAction(Actions.run(() -> statusbar.update(currentCmd, target)));
            sequence.addAction(Actions.run(() -> codeLine.setCompleting(false)));
            sequence.addAction(Actions.run(() -> {
                target.setAnimationSpeed(this.executionDelay / 4.0f);
                currentCmd.execute(target);
            }));
        }
        sequence.addAction(new TimedAction(this));
        sequence.addAction(Actions.run(editor::stop));
        return sequence;
    }

    private void setCompleting(int lineNum, CodeLine currentLine) {
        currentLine.setCompleting(true);
        currentLineNum = lineNum;
    }

    public void win() {
        stop();
        statusbar.win();
    }

    public void stop() {
        targets.forEach(Actor::clearActions);
    }

    @Override
    public float getDelay() {
        return executionDelay;
    }

    public void setExecutionSpeed(float executionSpeed) {
        this.executionDelay = executionSpeed;
    }

    public Map<CodeLine, Command> getCodeMap() { return codeMap; }

    public Editor getEditor() { return editor; }

    public List<Collidable> getZones() { return zones; }

    public int getCurrentLineNum() {
        return currentLineNum;
    }
}
