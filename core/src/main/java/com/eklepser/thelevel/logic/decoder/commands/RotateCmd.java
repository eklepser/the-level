package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Direction;

public class RotateCmd extends Command {
    private final Entity target;
    private final Direction direction;

    public RotateCmd(Instruction instruction, Entity target, Direction direction) {
        super(instruction);
        this.target = target;
        this.direction = direction;
    }

    @Override
    public void execute() {
        System.out.println("ROTATE " + direction.name());
        switch (direction) {
            case LEFT:
                target.rotate(Direction.LEFT);
                break;
            case RIGHT:
                target.rotate(Direction.RIGHT);
                break;
        }
    }
}
