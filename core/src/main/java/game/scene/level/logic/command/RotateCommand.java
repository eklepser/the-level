package game.scene.level.logic.command;

import game.common.logic.Direction;
import game.common.logic.entity.Entity;

public final class RotateCommand extends Command {
    private final Direction direction;

    public RotateCommand(int lineNum, Direction direction) {
        super(lineNum);
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("ROTATE " + direction.name());
        target.rotate(direction);
    }

    public Direction getDirection() { return direction; }
}
