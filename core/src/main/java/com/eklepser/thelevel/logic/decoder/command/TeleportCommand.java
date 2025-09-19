package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class TeleportCommand implements Command {
    private final Vector2 worldPos;

    public TeleportCommand(Vector2 worldPos) {
        this.worldPos = worldPos;
    }

    @Override
    public void execute(Entity target) {
       target.teleport(worldPos);
    }
}
