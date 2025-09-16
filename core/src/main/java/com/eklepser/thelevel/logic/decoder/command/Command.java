package com.eklepser.thelevel.logic.decoder.command;

import com.eklepser.thelevel.logic.world.collision.Entity;

public abstract class Command {
    public Command() { }

    public abstract void execute(Entity target);
}
