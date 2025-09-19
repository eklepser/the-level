package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand(String[] args, Executor executor) {
        this.direction = Direction.getByName(args[0]);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.setMoving(direction);
    }
}
