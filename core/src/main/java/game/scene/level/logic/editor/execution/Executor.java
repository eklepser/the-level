package game.scene.level.logic.editor.execution;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import game.common.CollisionContext;
import game.common.logic.zone.Zone;
import game.scene.level.rendering.LevelScreen;
import game.scene.level.rendering.component.editor.CodeLine;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.scene.level.logic.editor.command.Command;
import game.common.logic.entity.Entity;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Executor implements TimeController {
    private final CollisionContext collisionContext;

    private final Translator translator;
    private Map<Integer, Command> codeMap;

    private float executionDelay = 0.5f;
    private int currentLineNum;

    public Executor(LevelConfiguration config, CollisionContext collisionContext) {
        translator = new Translator(config.allowedInstructions, this);
        this.collisionContext = collisionContext;

        //codeLines = editorLayout.getCodeLayout().getCodeLines();
    }

    @Override
    public float getDelay() {
        return executionDelay;
    }

    // Class logic:
    public String runExecution(List<String> inputLines) {
        TranslationResult result = translator.translateAll(inputLines);
        if (result.success()) {
            codeMap = translator.getCodeMap();
            execute(0, codeMap);
            //editorLayout.getRoot().getStatusBar().start();
        }
        return result.message();
    }

    public void execute(int start, Map<Integer, Command> codeMap) {
        for (Entity target : collisionContext.entities()) {
            target.clearActions();
            target.addAction(createCommandAction(start, codeMap, target));
        }
    }

    public SequenceAction createCommandAction(int start, Map<Integer, Command> codeMap, Entity target) {
        System.out.println("Running");
        SequenceAction sequence = new SequenceAction();

        for (int i = start; i < codeMap.size(); i++) {
            Command currentCmd = codeMap.get(i);
            if (currentCmd == null) continue;

            int finalI = i;

            //sequence.addAction(Actions.run(() -> codeLine.setCompleting(true)));
            sequence.addAction(Actions.run(() -> currentLineNum = finalI));
            sequence.addAction(new TimedAction(this));
            //sequence.addAction(Actions.run(() -> editorLayout.getRoot().getStatusBar().update(currentCmd, target)));
            //sequence.addAction(Actions.run(() -> codeLine.setCompleting(false)));

            sequence.addAction(Actions.run(() -> {
                target.setAnimationSpeed(this.executionDelay / 4.0f);
                currentCmd.execute(target);
            }));
        }

        sequence.addAction(new TimedAction(this));
        //sequence.addAction(Actions.run(editorLayout::stop));
        return sequence;
    }

    public void win() {
        stop();
        //editorLayout.getRoot().getStatusBar().win();
    }

    public void stop() {
        collisionContext.entities().forEach(Actor::clearActions);
    }

    // Getters & setters:
    public CollisionContext getCollisionContext() {
        return collisionContext;
    }

    public Map<Integer, Command> getCodeMap() {
        return codeMap;
    }

    public int getCurrentLineNum() { return currentLineNum; }
}
