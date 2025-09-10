package com.eklepser.thelevel.logic.decoder.commands;

import com.eklepser.thelevel.logic.world.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCmd extends Command {
    private final Direction direction;

    public MoveCmd(Instruction instruction, Direction direction) {
        super(instruction);
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.move(direction);
    }
}
