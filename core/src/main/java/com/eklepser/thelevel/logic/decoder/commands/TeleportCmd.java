package com.eklepser.thelevel.logic.decoder.commands;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.logic.world.Entity;

public class TeleportCmd extends Command {
    private final Entity target;
    private final Vector2 worldPos;

    public TeleportCmd(Instruction instruction, Entity target, Vector2 worldPos) {
        super(instruction);
        this.target = target;
        this.worldPos = worldPos;
    }

    @Override
    public void execute() {
        target.moveTo(worldPos);
    }
}
