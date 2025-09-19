package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class RotateCommand implements Command {
    private final Direction direction;
    public RotateCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("ROTATE " + direction.name());
        target.rotate(direction);
    }
}
