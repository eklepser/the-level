package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class TeleportCommand extends Command {
    private final Vector2 worldPos;

    public TeleportCommand(String[] args) {
        float posX = Float.parseFloat(args[0]);
        float posY = Float.parseFloat(args[1]);
        this.worldPos = new Vector2(posX, posY);
    }

    @Override
    public void execute(Entity target) {
        target.teleport(worldPos);
    }

    @Override
    public Image[] getIcons(Entity target) {
        return null;
    }
}
