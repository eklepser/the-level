package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCmd extends Command {
    private final Direction direction;
    private final Entity target;

    public MoveCmd(Instruction instruction, Entity target, Direction direction) {
        super(instruction);
        this.target = target;
        this.direction = direction;
    }

    @Override
    public void execute() {
        System.out.println("MOVE " + direction.name());
        switch (direction) {
            case UP:
                target.move(Direction.UP);
                break;
            case DOWN:
                target.move(Direction.DOWN);
                break;
            case LEFT:
                target.move(Direction.LEFT);
                break;
            case RIGHT:
                target.move(Direction.RIGHT);
                break;
        }
    }
}
