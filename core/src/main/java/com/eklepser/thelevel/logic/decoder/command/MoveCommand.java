package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCommand implements Command {
    private final Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.setMoving(direction);
    }
}
