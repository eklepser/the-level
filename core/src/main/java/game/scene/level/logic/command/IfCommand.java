package game.scene.level.logic.command;

import game.scene.common.logic.collision.CollisionContext;
import game.scene.common.logic.entity.Entity;
import game.scene.level.logic.condition.Condition;
import game.scene.level.logic.execution.Executor;

public final class IfCommand extends Command {
    private final Condition condition;
    private final Command innerCommand;
    private final CollisionContext collisionContext;

    private boolean isTrue = false;

    public IfCommand(int lineNum, Condition condition, Command innerCommand, Executor executor) {
        super(lineNum);
        this.condition = condition;
        this.innerCommand = innerCommand;
        collisionContext = executor.getCollisionContext();
    }

    @Override
    public void execute(Entity target) {
        isTrue = condition.check(target, collisionContext);
        if (isTrue) {
            innerCommand.execute(target);
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public Command getInnerCommand() {
        return innerCommand;
    }

    public boolean isTrue() {
        return isTrue;
    }
}
