package game.scene.level.logic.command;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.collision.CollisionContext;
import game.scene.level.logic.condition.Condition;
import game.scene.level.logic.execution.Executor;
import game.common.logic.entity.Entity;

import java.util.Arrays;

public class IfCommand extends Command {
    private final Condition condition;
    private final Command innerCommand;
    private final CollisionContext collisionContext;

    public IfCommand(int lineNum, Condition condition, Command innerCommand, Executor executor) {
        super(lineNum);
        this.condition = condition;
        this.innerCommand = innerCommand;
        collisionContext = executor.getCollisionContext();
    }

    @Override
    public void execute(Entity target) {
        System.out.println("IF");
        if (condition.check(target, collisionContext))
        {
            System.out.println("EXECUTING INNER COMMAND");
            innerCommand.execute(target);
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public Command getInnerCommand() {
        return innerCommand;
    }
}
