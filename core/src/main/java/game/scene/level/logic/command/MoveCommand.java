package game.scene.level.logic.command;

import game.scene.common.logic.Direction;
import game.scene.common.logic.entity.Entity;

public final class MoveCommand extends Command {
    private final Direction direction;
    private Direction concreteDirection;

    public MoveCommand(int lineNum, Direction direction) {
        super(lineNum);
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        if (direction.equals(Direction.FORWARD)) {
            System.out.println(target.getFacingDirection());
            concreteDirection = target.getFacingDirection();
        }
        else {
            concreteDirection = direction;
        }
        target.setTargetWorldPos(concreteDirection);
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getConcreteDirection() {
        return concreteDirection;
    }
}
