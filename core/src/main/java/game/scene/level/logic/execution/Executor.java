package game.scene.level.logic.execution;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import game.common.logic.collision.CollisionContext;
import game.common.logic.entity.Entity;
import game.scene.level.logic.Level;
import game.scene.level.logic.LevelConfiguration;
import game.scene.level.logic.command.Command;
import game.scene.level.logic.event.NewCommandEvent;

import java.util.List;
import java.util.Map;

public final class Executor implements TimeController {
    private final CollisionContext collisionContext;
    private final Level level;

    private final Translator translator;
    private Map<Integer, Command> codeMap;

    private float executionDelay = 0.5f;

    public Executor(LevelConfiguration config, Level level) {
        translator = new Translator(config.allowedInstructions, this);
        this.level = level;
        collisionContext = level.getCollisionContext();
    }

    // Class logic:
    public void runExecution(List<String> inputLines) {
        TranslationResult result = translator.translateAll(inputLines);
        if (result.success()) {
            codeMap = translator.getCodeMap();
            execute(0, codeMap);
        }
    }

    public void execute(int start, Map<Integer, Command> codeMap) {
        for (Entity target : collisionContext.entities()) {
            target.clearActions();
            target.addAction(createCommandAction(start, codeMap, target));
        }
    }

    private SequenceAction createCommandAction(int start, Map<Integer, Command> codeMap, Entity target) {
        System.out.println("Running");
        SequenceAction sequence = new SequenceAction();

        for (int i = start; i < codeMap.size(); i++) {

            Command currentCmd = codeMap.get(i);
            if (currentCmd == null) continue;

            // time delay
            sequence.addAction(new TimedAction(this));

            // add new command to target
            sequence.addAction(Actions.run(() -> {
                target.setAnimationSpeed(executionDelay / 4.0f);
                currentCmd.execute(target);
            }));

            // notify all level subscribers
            sequence.addAction(Actions.run(() ->
                level.fire(new NewCommandEvent(currentCmd))));
        }

        return sequence;
    }

    public void stop() {
        collisionContext.entities().forEach(Actor::clearActions);
    }

    // Getters & setters:
    @Override
    public float getDelay() {
        return executionDelay;
    }

    public void setExecutionDelay(float executionDelay) {
        this.executionDelay = executionDelay;
    }

    public CollisionContext getCollisionContext() {
        return collisionContext;
    }

    public Map<Integer, Command> getCodeMap() {
        return codeMap;
    }
}
