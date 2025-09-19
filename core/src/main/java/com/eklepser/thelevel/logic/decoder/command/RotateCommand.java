package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class RotateCommand extends Command {
    private final Direction direction;
    public RotateCommand(String[] args) {
        this.direction = Direction.getByName(args[0]);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("ROTATE " + direction.name());
        target.rotate(direction);
    }
}
