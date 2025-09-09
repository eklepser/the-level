package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.world.Cat;
import com.eklepser.thelevel.util.Direction;

public class MoveCmd extends Command {
    private final Direction direction;
    private final Cat target;

    public MoveCmd(Instruction instruction, Cat target, Direction direction) {
        super(instruction);
        this.target = target;
        this.direction = direction;
    }

    @Override
    public void execute() {
        switch (direction) {
            case UP:
                System.out.println("up");
                target.move(Direction.UP);
                break;
            case DOWN:
                System.out.println("down");
                target.move(Direction.DOWN);
                break;
            case LEFT:
                System.out.println("left");
                target.move(Direction.LEFT);
                break;
            case RIGHT:
                System.out.println("right");
                target.move(Direction.RIGHT);
                break;
        }
    }
}
