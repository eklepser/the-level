package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.game.editor.CodeLine;
import com.eklepser.thelevel.logic.decoder.commands.Command;
import com.eklepser.thelevel.logic.world.collision.Entity;

import java.util.List;
import java.util.Map;

public class Executor implements TimeController {
    private final List<CodeLine> codeLines;
    private Map<CodeLine, Command> codeMap;
    private final List<Entity> targets;
    private float executionDelay = 0.5f;

    public Executor(List<CodeLine> codeLines, List<Entity> targets) {
        this.codeLines = codeLines;
        this.targets = targets;
    }

    public String checkAndExecute() {
        Translator translator = new Translator(this, codeLines);
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            codeMap = result.getCodeMap();
            System.out.println(result.message());
            execute(0, codeMap);
        }
        else {
            System.out.println(result.message());
        }
        return result.message();
    }

    public void execute(int start, Map<CodeLine, Command> codeMap) {
        for (Entity target : targets) {
            target.clearActions();
            SequenceAction action = getSequenceAction(start, codeMap, target);
            target.addAction(action);
        }
    }

    @Override
    public float getDelay() {
        return executionDelay;
    }

    private SequenceAction getSequenceAction(int start, Map<CodeLine, Command> codeMap, Entity target) {
        System.out.println("Running");
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            Command currentCmd = codeMap.get(currentLine);
            if (currentCmd == null) continue;
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(true)));
            sequence.addAction(new TimedAction(this));
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(false)));

            sequence.addAction(Actions.run(() -> {
                target.setAnimationSpeed(this.executionDelay / 4.0f); // ← Читаем текущее значение!
                currentCmd.execute(target);
            }));
        }
        return sequence;
    }

    public Map<CodeLine, Command> getCodeMap() {
        return codeMap;
    }

    public void stop() {
        targets.forEach(Actor::clearActions);
    }

    public void setExecutionSpeed(float executionSpeed) {
        this.executionDelay = executionSpeed;
    }
}
