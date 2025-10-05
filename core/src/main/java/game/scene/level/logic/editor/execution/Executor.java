package game.scene.level.logic.editor.execution;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import game.scene.level.rendering.component.editor.CodeLine;
import game.scene.level.rendering.component.editor.EditorLayout;
import game.scene.level.logic.editor.command.Command;
import game.common.logic.entity.Entity;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;

import java.util.List;
import java.util.Map;

public final class Executor implements TimeController {
    private final Translator translator;
    private final List<CodeLine> codeLines;
    private final EditorLayout editorLayout;
    private Map<CodeLine, Command> codeMap;

    private final List<Entity> targets;

    private float executionDelay = 0.5f;
    private int currentLineNum;

    public Executor(Level level, EditorLayout editorLayout) {
        this.editorLayout = editorLayout;

        targets = level.getEntities();

        codeLines = editorLayout.getCodeLayout().getCodeLines();
        LevelConfiguration config = level.getConfig();
        translator = new Translator(config.allowedInstructions, codeLines, this);
    }

    @Override
    public float getDelay() {
        return executionDelay;
    }

    // Class logic:
    public String runExecution() {
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            codeMap = translator.getCodeMap();
            execute(0, codeMap);
            editorLayout.getRoot().getStatusBar().start();
        }
        return result.message();
    }

    public void execute(int start, Map<CodeLine, Command> codeMap) {
        for (Entity target : targets) {
            target.clearActions();
            target.addAction(createCommandAction(start, codeMap, target));
        }
    }

    public SequenceAction createCommandAction(int start, Map<CodeLine, Command> codeMap, Entity target) {
        System.out.println("Running");
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            int finalI = i;
            CodeLine codeLine = codeLines.get(i);
            Command currentCmd = codeMap.get(codeLine);
            if (currentCmd == null) continue;
            sequence.addAction(Actions.run(() -> codeLine.setCompleting(true)));
            sequence.addAction(Actions.run(() -> currentLineNum = finalI));
            sequence.addAction(new TimedAction(this));
            sequence.addAction(Actions.run(() -> editorLayout.getRoot().getStatusBar().update(currentCmd, target)));
            sequence.addAction(Actions.run(() -> codeLine.setCompleting(false)));
            sequence.addAction(Actions.run(() -> {
                target.setAnimationSpeed(this.executionDelay / 4.0f);
                currentCmd.execute(target);
            }));
        }
        sequence.addAction(new TimedAction(this));
        sequence.addAction(Actions.run(editorLayout::stop));
        return sequence;
    }

    public void win() {
        stop();
        editorLayout.getRoot().getStatusBar().win();
    }

    public void stop() {
        targets.forEach(Actor::clearActions);
    }

    // Getters & setters:
    public void setExecutionSpeed(float executionSpeed) { this.executionDelay = executionSpeed; }

    public Map<CodeLine, Command> getCodeMap() { return codeMap; }

    public EditorLayout getEditor() { return editorLayout; }

    public int getCurrentLineNum() { return currentLineNum; }
}
