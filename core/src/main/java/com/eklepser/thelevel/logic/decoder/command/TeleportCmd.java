package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class TeleportCmd extends Command {
    private final Vector2 worldPos;

    public TeleportCmd(Vector2 worldPos) {
        this.worldPos = worldPos;
    }

    @Override
    public void execute(Entity target) {
       target.teleport(worldPos);
    }
}
